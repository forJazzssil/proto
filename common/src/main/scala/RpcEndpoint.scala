trait RpcEndpoint {

	val rpcEnv: RpcEnv

	def receive: PartialFunction[Any, Unit] = {
		case _ => throw new Exception("does not implement 'receive'")
	}

	def onError(cause: Throwable): Unit = {
		throw cause
	}

	def onConnected():Unit = {}

	def onDisconnected():Unit = {}

	def onStart():Unit = {}

	def onStop():Unit = {}
}
