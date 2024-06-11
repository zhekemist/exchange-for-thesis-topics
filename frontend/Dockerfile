FROM nginx:1.27-alpine
COPY src/ /home/topic-exchange/content/
COPY nginx.conf /etc/nginx/nginx.conf
COPY ssl-certificate.pem ssl-key.pem /home/topic-exchange/security/
