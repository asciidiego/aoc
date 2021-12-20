FROM alpine:latest

RUN mkdir /opt/OrgFiles

WORKDIR /opt/OrgFiles

RUN apk add emacs-nox
RUN apk add python3
RUN apk add curl
RUN apk add bash
RUN apk add openjdk17-jdk
# RUN apk add clojure --repository=https://dl-cdn.alpinelinux.org/alpine/edge/testing

RUN curl -O https://download.clojure.org/install/linux-install-1.10.3.1040.sh 
RUN chmod +x linux-install-1.10.3.1040.sh
RUN ./linux-install*sh
RUN clojure -e "(println \"OK\")"


COPY *.el /opt/

RUN emacs --batch --load /opt/army-config.el

ENTRYPOINT ["emacs", "-Q", "--script"]

CMD ["/opt/publish.el"]
