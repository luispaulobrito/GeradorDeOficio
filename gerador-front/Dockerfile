FROM node:18.18.1-alpine3.18
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json /app/package.json
COPY . /app/

RUN npm install -g @angular/cli@16.2.6 @angular-devkit/build-angular && npm install
EXPOSE 4200
EXPOSE 9876

CMD [ "npm", "run", "start"]
