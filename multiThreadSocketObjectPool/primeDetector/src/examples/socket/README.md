This example code illustrates:
1. Server socket creation.
2. Client socket creation.
3. IPC among processes using sockets.

Note: 
1. Server keeps on listening to connections on the specified port.
2. Once it receives a connection request, it spawns a new thread for that particular request and starts the new thread thus freeing itself to listen for other connections.
3. The SocketHandler thread further listens for communication from the client through that socket using DataInputStream.

Commands:
Compile: javac Client.java Server.java Results.java
Run: 
1. Start server first: java Server <portNUm> <resultFileName>
2. Start the client next: java Client <ipAddr_ofServer> <port_ofServer>
