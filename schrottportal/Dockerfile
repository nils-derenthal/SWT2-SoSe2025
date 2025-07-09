ARG BUILD_DIR="/build"

FROM node:18-alpine AS build
ARG BUILD_DIR
WORKDIR $BUILD_DIR
COPY package.json package-lock.json $BUILD_DIR/
RUN npm install
COPY . $BUILD_DIR/
RUN npm run build

FROM nginx:alpine
ARG BUILD_DIR
COPY --from=build $BUILD_DIR/dist/schrottportal /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
