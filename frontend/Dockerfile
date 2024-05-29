FROM node:22-alpine
WORKDIR /app
COPY . .
RUN npm install 
RUN npm install -g @angular/cli@18.0.0
# RUN ng build
# RUN ng build
CMD ["node", "server.ts"]            

# FROM nginx:latest
# COPY nginx.conf /etc/nginx/nginx.conf
# COPY --from=build /app /usr/share/nginx/html
