docker run -d -p 8080:8080 -p 8983:8983 -v /storefrontdata/solr:/opt/solr/example/solr/collection1/data -v /storefrontdata/tomcat/logs:/usr/local/tomcat/logs -v /storefrontdata/tomcat/data:/var/openstorefront razaltan/storefront