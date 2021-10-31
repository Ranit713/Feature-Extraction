module example ( sys_clk, pi1, pi2, pi3, po1, po2  );

input sys_clk, pi1, pi2, pi3;
output po1, po2;
wire  n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13;

assign p1 = n1;
assign p1 = n3;
assign p1 = n2;
assign p1 = n5;
assign po1 = n10;

AND2X1 A ( .IN1(n1), .IN2(n2), .Q(n7) );
AND2X1 D ( .IN1(n9), .IN2(n9), .Q(n10) );
OR2X2 C ( .IN1(n7), .IN2(n8), .Q(n9) );
XOR2X2 B ( .IN1(n5), .IN2(n6), .Q(n8) );
DFFARX1 FFA ( .D(n3), .CLK(sys_clk), .RSTB(n4), .Q(n6), .QN() );
DFFARX1 FFB ( .D(n10), .CLK(sys_clk), .RSTB(n11), .Q(n12), .QN(n13) );

endmodule