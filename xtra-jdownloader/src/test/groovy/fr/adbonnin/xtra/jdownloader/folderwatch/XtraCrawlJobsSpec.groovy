package fr.adbonnin.xtra.jdownloader.folderwatch

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.BooleanNode
import com.fasterxml.jackson.databind.node.IntNode
import com.fasterxml.jackson.databind.node.TextNode
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Shared
import spock.lang.Specification

class XtraCrawlJobsSpec extends Specification {

    @Shared
    def mapper = new ObjectMapper()

    @Rule
    TemporaryFolder testFolder = new TemporaryFolder()

    def "should build crawl job file"() {
        given:
        def jdownloaderDir = testFolder.newFolder()

        when:
        def file = XtraCrawlJobs.newCrawlJobCleanedFile(jdownloaderDir, 'test')

        then:
        file.name == 'test.crawljob'
        file.parentFile == new File(jdownloaderDir, XtraCrawlJobs.FOLDERWATCH_DIRNAME)
    }

    def "should write default CrawlJob"() {
        when:
        def crawlJobsStr = XtraCrawlJobs.write([new CrawlJob()])
        def crawlJobs = mapper.readTree(crawlJobsStr)

        then:
        crawlJobs.size() == 1

        with(crawlJobs[0]) {
            size() == 4
            get('chunks') == new IntNode(0)
            get('addOfflineLink') == BooleanNode.getTrue()
            get('overwritePackagizerEnabled') == BooleanNode.getTrue()
            get('deepAnalyseEnabled') == BooleanNode.getFalse()
        }
    }

    def "should write CrawlJob"() {
        given:
        def crawlJob = new CrawlJob()

        crawlJob.with {
            filename = 'filename'
            chunks = 1
            autoConfirm = true
            addOfflineLink = false

            autoStart = true
            forcedStart = true
            enabled = true
            text = 'text'
            deepAnalyseEnabled = true
            packageName = 'packageName'
            priority = CrawlJob.Priority.HIGHEST
            extractAfterDownload = true

            downloadFolder = 'downloadFolder'
            extractPasswords = ['extractPasswords']
            downloadPassword = 'downloadPassword'
            overwritePackagizerEnabled = false
        }

        when:
        def crawlJobsStr = XtraCrawlJobs.write([crawlJob])
        def crawlJobs = mapper.readTree(crawlJobsStr)

        then:
        with(crawlJobs[0]) {
            size() == 16

            get('filename') == new TextNode('filename')
            get('chunks') == new IntNode(1)
            get('autoConfirm') == BooleanNode.getTrue()
            get('addOfflineLink') == BooleanNode.getFalse()

            get('autoStart') == BooleanNode.getTrue()
            get('forcedStart') == BooleanNode.getTrue()
            get('enabled') == BooleanNode.getTrue()
            get('text') == new TextNode('text')
            get('deepAnalyseEnabled') == BooleanNode.getTrue()
            get('packageName') == new TextNode('packageName')
            get('priority') == new TextNode('HIGHEST')
            get('extractAfterDownload') == BooleanNode.getTrue()

            get('downloadFolder') == new TextNode('downloadFolder')
            get('extractPasswords').get(0) == new TextNode('extractPasswords')
            get('downloadPassword') == new TextNode('downloadPassword')
            get('overwritePackagizerEnabled') == BooleanNode.getFalse()
        }
    }

    def "should write CrawlJob priority"() {
        given:
        def crawlJobPriority = new CrawlJob()
        crawlJobPriority.priority = CrawlJob.Priority.HIGHER

        when:
        def crawlJobsStr = XtraCrawlJobs.write([crawlJobPriority])
        def crawlJobs = mapper.readTree(crawlJobsStr)

        then:
        def crawlJob = crawlJobs[0]
        crawlJob.get('priority') == new TextNode('HIGHER')
    }

    def "should update priority"() {
        given:
        def crawlJob = new CrawlJob()

        expect:
        crawlJob.priority == null

        when:
        crawlJob."$priorityMethod"()

        then:
        crawlJob.priority == expectedPriority

        where:
        priorityMethod       | expectedPriority
        'setHighestPriority' | CrawlJob.Priority.HIGHEST
        'setHigherPriority'  | CrawlJob.Priority.HIGHER
        'setHighPriority'    | CrawlJob.Priority.HIGH
        'setDefaultPriority' | CrawlJob.Priority.DEFAULT
        'setLowerPriority'   | CrawlJob.Priority.LOWER
    }

    def "should write CrawlJob to file"() {
        given:
        def crawlJobs = [new CrawlJob()]
        def file = testFolder.newFile()

        when:
        def str = XtraCrawlJobs.write(crawlJobs)
        XtraCrawlJobs.write(crawlJobs, file)

        then:
        str == file.text
    }
}
