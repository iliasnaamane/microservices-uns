FROM tomee:8-jre-7.0.3-plus


WORKDIR /usr/local/tomee/

COPY ./target/service_report.war ./webapps/.

HEALTHCHECK --interval=5s CMD curl --fail http://localhost:8080/ || exit 1

EXPOSE 8080
