import io.grpc.examples.helloworld.{GreeterGrpc, Person, ProtoRequest, ProtoResponse}
import io.grpc.stub.StreamObserver

import scala.concurrent.Future

object HelloServer extends GRPCServer{
	class HelloService extends GreeterGrpc.GreeterImplBase{
		override def receive(request: ProtoRequest, responseObserver: StreamObserver[ProtoResponse]): Unit = {
			val name = request.getPerson match {
				case Person => request.getPerson.getName
				case _ => "a"
			}
			val response = ProtoResponse.newBuilder().setMessage(name).build()
			responseObserver.onNext(response)
			responseObserver.onCompleted()
		}
	}

	def main(args: Array[String]): Unit = {

	}
}
