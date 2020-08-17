import io.grpc.{ServerBuilder, ServerServiceDefinition}
import io.grpc.examples.helloworld.{GreeterGrpc, Person, ProtoRequest, ProtoResponse}
import io.grpc.stub.StreamObserver

import scala.concurrent.Future

object HelloServer extends GRPCServer{
	object HelloService extends GreeterGrpc.GreeterImplBase{
		override def receive(request: ProtoRequest, responseObserver: StreamObserver[ProtoResponse]): Unit = {
			val name = request.getPerson match {
				case p:Person => p.getName
				case _ => "a"
			}
			println("p : " + name)
			val response = ProtoResponse.newBuilder().setMessage(name).build()
			responseObserver.onNext(response)
			responseObserver.onCompleted()
		}
	}

	def main(args: Array[String]): Unit = {
		val server = ServerBuilder.forPort(50052).addService(HelloService).build().start()
		server.awaitTermination()
	}
}
