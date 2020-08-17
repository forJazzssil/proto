import io.grpc.Server;
import io.grpc.ServerBuilder;

public class HelloServer {

	private int port = 5050;
	private Server server;

	public void start(){
//		server = ServerBuilder.forPort(port).addService(new ).build().start();
		System.out.println("server started ! ");

		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run(){
				System.out.println("hutting down gRPC server since JVM is shutting down");
				HelloServer.this.stop();
				System.err.println("server shut down");
			}
		});
	}

	private void stop(){
		if (server !=null){server.shutdown();}
	}

	public void blockutilshutdown() throws InterruptedException {
		if(server != null) server.awaitTermination();
	}

//	private class GreeterImpl extends
}
