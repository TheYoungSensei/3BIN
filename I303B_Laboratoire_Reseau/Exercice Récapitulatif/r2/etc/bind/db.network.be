$TTL	604800
@	IN	SOA	r2.network.be. admin.network.be. (
			      2		; Serial
			 604800		; Refresh
			  86400		; Retry
			2419200		; Expire
			 604800 )	; Negative Cache TTL
;
@	IN	NS	r3.network.be.
@	IN	A	127.0.0.1
@	IN	AAAA	::1
s1	IN	A	192.168.5.2
aliasS1 IN CNAME s1
s2	IN	A	192.168.5.3
aliasS2 IN CNAME s2	

