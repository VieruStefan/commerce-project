FROM node:22-alpine
WORKDIR /app
COPY . .
RUN npm install 
RUN npm install -g @angular/cli@18.0.0
EXPOSE 4200
CMD ["npm", "start"]            
