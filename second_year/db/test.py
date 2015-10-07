import pydocumentdb.document_client as document_client

host = 'https://doc01.documents.azure.com'
masterKey = 'PhA7+FAD9uHw+McP/sM4CFZA4dUnOrzmgnk0J+SEcvOC5ac7UYNu/EHvOEBcOpEtj6Vz/YESDoxxxsnoaLDCGg=='

client = document_client.DocumentClient(host, {'masterKey': masterKey})
databases = client.ReadDatabases()

db = [db for db in databases if db['id'] == 'uvt01'][0]

for collection in client.ReadCollections(db['_self']):
    if collection['id'] == 'info':
        for document in client.ReadDocuments(collection['_self']):
            new_document = {key: value for key, value in document.iteritems() if not key.startswith("_")}
            print new_document
