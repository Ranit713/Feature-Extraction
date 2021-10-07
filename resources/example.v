module s38584 ( CK, g100, g10122, g10306);

input CK, g100, g11;
output g10122, g10306, g10500, g10527;
wire  g34956, g5057, g33046;

assign g34839 = g34956;
assign g29217 = g21270;
assign g29220 = g21245;
LSDNENX1 U5116 ( .D(g34783), .ENB(n2730), .Q(g34221) );
LSDNENX1 U5126 ( .D(n4836), .ENB(n4896), .Q(n4895) );
SDFFX1 DFF_5_Q_reg ( .D(g22026), .SI(g2883), .SE(test_se), .CLK(CK), .Q(
        g2888), .QN() );

endmodule