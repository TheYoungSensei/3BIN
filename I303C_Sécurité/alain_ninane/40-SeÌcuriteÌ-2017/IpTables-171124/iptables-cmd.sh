iptables -A PREROUTING -t nat -s 130.104.0.0/16 -i eth0 -p tcp -m tcp --dport 8080 -j REDIRECT --to-ports 3128

iptables -A PREROUTING -t nat -i eth0 -p tcp -m tcp --dport 122 -j DNAT --to-destination 10.0.0.43:22

iptables -A POSTROUTING -s 10.0.0.0/255.255.255.0 -o eth0 -j MASQUERADE
