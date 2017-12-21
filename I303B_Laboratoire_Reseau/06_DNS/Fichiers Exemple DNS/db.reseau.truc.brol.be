;
; BIND data file for local loopback interface
;
$TTL	604800
@	IN	SOA	ns.reseau.truc.brol.be. admin.reseau.truc.brol.be. (
			      2		; Serial
			 604800		; Refresh
			  86400		; Retry
			2419200		; Expire
			 604800 )	; Negative Cache TTL
;
@	IN	NS	r3.network.be.
@	IN	A	127.0.0.1
@	IN	AAAA	::1
r3	IN	A	192.168.3.1
h1	IN	A	192.168.2.2
h2	IN	A	192.168.2.3	
aliasH1 IN CNAME h1