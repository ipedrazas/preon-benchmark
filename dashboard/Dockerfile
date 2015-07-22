FROM nginx

COPY nginx.conf /etc/nginx/nginx.conf
COPY dashboard.conf /etc/nginx/conf.d/default.conf
COPY dist /usr/share/nginx/html

EXPOSE 8000
