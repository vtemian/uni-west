import socket

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

server_address = ('localhost', 6666)
print "connecting to %s port %s" % server_address
sock.connect(server_address)

try:
    while True:
        message = raw_input("get a message: ")
        print 'sending "%s"' % message
        sock.sendall(message)
finally:
    print 'closing socket'
    sock.close()
