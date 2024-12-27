package io.sportsmrm.grpc.secure

import io.sportsmrm.grpc.secure._
import org.scalatest.funsuite.AnyFunSuite

class MyTest extends AnyFunSuite {
    test("TestMessageFieldNums is generated") {
        assert(TestMessage.A_FIELD_NUMBER == 1)
        assert(TestMessage.B_FIELD_NUMBER == 2)
    }

    test("NestedMessage is generated") {
        assert(TestMessage.NestedMessage.COLOR_FIELD_NUMBER == 1)
    }
}
