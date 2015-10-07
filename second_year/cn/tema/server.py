import socket


sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.bind(('localhost', 6666))

sock.listen(1)

while True:
    print 'waiting for a connection'
    connection, client_address = sock.accept()
    print client_address

    try:
        while True:
            data = connection.recv(200)
            if len(data) > 0:
                print 'received "%s"' % data
    finally:
        connection.close()
