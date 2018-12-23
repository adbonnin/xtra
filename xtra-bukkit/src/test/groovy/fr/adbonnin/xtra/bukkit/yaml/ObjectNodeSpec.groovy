package fr.adbonnin.xtra.bukkit.yaml

import spock.lang.Specification

class ObjectNodeSpec extends Specification {

    void "should get fields"() {
        when:
        def obj = XtraYaml.read(yaml)

        then:
        obj.size() == expected.size()
        obj.isEmpty() == expected.isEmpty()

        !obj.isArray()
        obj.isObject()

        obj.get(0) == null
        obj.get('a') == new IntNode(1)

        !obj.has(0)
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
