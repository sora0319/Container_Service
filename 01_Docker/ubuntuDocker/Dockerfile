FROM ubuntu:20.04
LABEL author="sora"
LABEL description="This is ubuntu 20.04 image and apache2, php5, git, crul, ssh, wget packages"
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive && \
    apt-get install -y tzdata && \
    ln -fs /usr/share/zoneinfo/Etc/Asia /etc/localtime && \
    dpkg-reconfigure -f noninteractive tzdata && \
    apt-get install -y apache2 php git curl ssh wget && \
    apt-get clean

ENV APACHE_PORT=80 \
    APACHE_DOCUMENT_ROOT=/var/www/html \
    APACHE_SERVER_ADMIN=webmaster@localhost \
    APACHE_LOG_LEVEL=warn \
    APACHE_SERVER_NAME=localhost \
    APACHE_RUN_DIR=/var/run/apache2 \
    APACHE_LOG_DIR=/var/log/apache2 \
    APACHE_PID_FILE=/var/run/apache2/apache2.pid \
    APACHE_RUN_USER=www-data \
    APACHE_RUN_GROUP=www-data

RUN mkdir -p ${APACHE_RUN_DIR} ${APACHE_LOG_DIR}

RUN echo "ServerAdmin ${APACHE_SERVER_ADMIN}" >> /etc/apache2/sites-available/000-default.conf && \
    echo "DocumentRoot ${APACHE_DOCUMENT_ROOT}" >> /etc/apache2/sites-available/000-default.conf && \
    echo "ServerName ${APACHE_SERVER_NAME}" >> /etc/apache2/apache2.conf && \
    echo "LogLevel ${APACHE_LOG_LEVEL}" >> /etc/apache2/apache2.conf && \
    echo "DefaultRuntimeDir ${APACHE_RUN_DIR}" >> /etc/apache2/apache2.conf && \
    echo "PidFile ${APACHE_PID_FILE}" >> /etc/apache2/apache2.conf && \
    echo "User ${APACHE_RUN_USER}" >> /etc/apache2/apache2.conf && \
    echo "Group ${APACHE_RUN_GROUP}" >> /etc/apache2/apache2.conf && \
    echo "ErrorLog ${APACHE_LOG_DIR}/error.log" >> /etc/apache2/apache2.conf && \
    echo "CustomLog ${APACHE_LOG_DIR}/access.log combined" >> /etc/apache2/apache2.conf


RUN echo '<!DOCTYPE html>' > ${APACHE_DOCUMENT_ROOT}/index.html && \
    echo '<html>' >> ${APACHE_DOCUMENT_ROOT}/index.html && \
    echo '<head><title>Welcome to My Custom Apache Server</title></head>' >> ${APACHE_DOCUMENT_ROOT}/index.html && \
    echo '<body><h1>Hello, World!</h1><p>This is a custom web page served by Apache.</p></body>' >> ${APACHE_DOCUMENT_ROOT}/index.html && \
    echo '</html>' >> ${APACHE_DOCUMENT_ROOT}/index.htmls

EXPOSE ${APACHE_PORT}/tcp
CMD [ "apache2", "-D", "FOREGROUND"]