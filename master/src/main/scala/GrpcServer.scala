import io.grpc.{ServerBuilder,ServerServiceDefinition}

trait GRPCServer {
	def runServer(service: ServerServiceDefinition): Unit = {
		val server = ServerBuilder
		  .forPort(50051)
		  .addService(service)
		  .build().start()

		// make sure our server is stopped when jvm is shut down
		Runtime.getRuntime.addShutdownHook(new Thread() {
			override def run(): Unit = server.shutdown()
		})

		server.awaitTermination()
	}

}