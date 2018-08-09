FROM node:10

#install bower && gulp-cli global
RUN npm i -g bower gulp-cli

WORKDIR /usr/src
ADD . .

# Install required dependencies
RUN yarn
RUN bower install --allow-root
RUN gulp build

WORKDIR /usr/dist

RUN cp -r /usr/src/dist/* .

FROM nginx

COPY --from=0 /usr/dist /usr/share/nginx/html
COPY ./nginx/default.conf /etc/nginx/conf.d/default.conf
