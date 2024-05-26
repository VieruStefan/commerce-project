FROM node:18-alpine AS build

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build --prod

FROM nginx:stable-alpine

COPY --from=build /app/dist/your-angular-app /usr/share/nginx/html

COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 4200

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
