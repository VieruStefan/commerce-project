FROM node:22-alpine as build
WORKDIR /app
COPY . .
# RUN npm install 
# RUN npm install -g @angular/cli@18.0.0
RUN npm ci
RUN npm run build --production
# CMD ["npm", "start"]            

FROM nginx:latest
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /app/frontend /usr/share/nginx/html
