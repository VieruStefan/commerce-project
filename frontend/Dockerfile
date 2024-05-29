FROM node:20-alpine as build
WORKDIR /app
COPY . .
RUN npm install
RUN npm install -g @angular/cli@18.0.0
RUN ng build

FROM nginx:latest
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /app /usr/share/nginx/html/frontend
