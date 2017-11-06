FROM node:8

WORKDIR /usr/src/app

COPY . .

HEALTHCHECK --interval=15s CMD curl --fail http://localhost:9060/ || exit 1

EXPOSE 9060

RUN npm install

CMD [ "npm", "run", "serve" ]
