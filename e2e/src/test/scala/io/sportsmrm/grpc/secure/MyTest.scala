package io.sportsmrm.grpc.secure

import io.sportsmrm.grpc.secure.test._
import org.scalatest.funsuite.AnyFunSuite

class MyTest extends AnyFunSuite {
    test("TestMessageFieldNums is generated") {
        assert(TestMessageFieldNums.a == 1)
        assert(TestMessageFieldNums.b == 2)
    }

    test("NestedMessage is generated") {
        assert(TestMessageFieldNums.NestedMessageFieldNums.color == 1)
    }
}
