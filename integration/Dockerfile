FROM petitroll/esb
# Installing OSGi dependencies at build time
COPY ./install-deps.sh .
RUN chmod 777 ./install-deps.sh
RUN ./install-deps.sh

# Build: docker build --no-cache -t iliasnaamane/wow-bus .
# Run:   docker run -v ./_in:/servicemix/camel/input -v ./_out:/servicemix/camel/output petitroll/tcs-bus
# Overriding: docker run -it --entrypoint "sh" petitroll/tcs-bus
# Run:   docker run -v ./_in:/servicemix/camel/input -v ./_out:/servicemix/camel/output result/esb-bus
RUN mkdir -p camel/input camel/output

# Deploying technical flows
COPY flows/target/implem-1.0.jar ./deploy/.

