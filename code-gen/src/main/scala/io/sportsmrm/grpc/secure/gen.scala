package io.sportsmrm.grpc.secure

import protocbridge.Artifact
import scalapb.GeneratorOption
import protocbridge.SandboxedJvmGenerator

object gen {
  def apply(
      options: GeneratorOption*
  ): (SandboxedJvmGenerator, Seq[String]) =
    (
      SandboxedJvmGenerator.forModule(
        "scala",
        Artifact(
          io.sportsmrm.grpc.secure.compiler.BuildInfo.organization,
          "secure-grpc-codegen_2.12",
          io.sportsmrm.grpc.secure.compiler.BuildInfo.version
        ),
        "io.sportsmrm.grpc.secure.compiler.CodeGenerator$",
        io.sportsmrm.grpc.secure.compiler.CodeGenerator.suggestedDependencies
      ),
      options.map(_.toString)
    )

  def apply(
      options: Set[GeneratorOption] = Set.empty
  ): (SandboxedJvmGenerator, Seq[String]) = apply(options.toSeq: _*)
}