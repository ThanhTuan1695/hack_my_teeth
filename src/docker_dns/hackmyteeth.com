;
; BIND data file for hackmyteeth
;
$TTL	604800
@	IN	SOA	localhost. root.localhost. (
			      2		; Serial
			 604800		; Refresh
			  86400		; Retry
			2419200		; Expire
			 604800 )	; Negative Cache TTL
;
@	IN	NS	localhost.
; name servers - A records
hackmyteeth.com.          IN      A       hackteeth_api