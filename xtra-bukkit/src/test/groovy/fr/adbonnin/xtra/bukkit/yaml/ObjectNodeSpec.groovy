package fr.adbonnin.xtra.bukkit.yaml

import fr.adbonnin.xtra.bukkit.yaml.node.IntNode
import fr.adbonnin.xtra.bukkit.yaml.node.ObjectNode
import spock.lang.Specification

class ObjectNodeSpec extends Specification {

    void "should get fields"() {
        when:
        def obj = XtraYaml.read(yaml) as ObjectNode

        then:
        obj.size() == expected.size()
        obj.isEmpty() == expected.isEmpty()

        !obj.isArray()
        obj.isObject()

        obj.path(0) == new IntNode(1)
        obj.has(0)

        obj.path('a') == new IntNode(1)
        obj.has('a')

        obj.iterator().toList() == expected.values().toList()
        obj.fieldNames().toList() == expected.keySet().toList()
        obj.fields().toList() == expected.entrySet().toList()

        where:
        yaml = """
a: 1
b: 2
c: 3
"""
        expected = [
            a: new IntNode(1),
            b: new IntNode(2),
            c: new IntNode(3)
        ] as LinkedHashMap
    }
}
