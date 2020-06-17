
public class fonction {

//--------------------------------------------------------	
	//----------------Puissance Gaz------------------
//--------------------------------------------------------
	
	static double tmp_amb_p_g(double tmp){
		
		double cor_t = 0;
	       if(tmp >-1 && tmp <= 5){
       		 cor_t = -0.00000134439*Math.pow(tmp, 5)+0.0000159427*Math.pow(tmp, 4)-0.000058546*Math.pow(tmp, 3)+0.000017426*Math.pow(tmp, 2)-0.00427784*tmp+1.19181;
       	  }else if(tmp > 5 && tmp < 29.9){
       		 cor_t = -0.00000000535367*Math.pow(tmp, 5)+0.000000562724*Math.pow(tmp, 4)-0.000020662*Math.pow(tmp, 3)+0.000281111*Math.pow(tmp, 2)-0.00620119*tmp+1.21398;
       	  }else if(tmp >= 29.9 && tmp < 46){
       		 cor_t = -0.000000385579*Math.pow(tmp, 5)+0.0000731412*Math.pow(tmp, 4)-0.00551315*Math.pow(tmp, 3)+0.206316*Math.pow(tmp, 2)-3.84221*tmp+29.6105;
       	  }
		
		return cor_t;
	}
  //-----------------	
	static double pression_p_g(double press){
		press = press * 1000 / 750.06375541921;
		double cor_p = -0.0160862*Math.pow(press/1000, 2)+1.05206*(press/1000)-0.0492256;
		return cor_p;
	}
//-------------------------	
	static double humidite_p_g(double humid, double tmp){
		 
		double lb_c46=-0.0000000000010731*Math.pow(humid, 5)+0.000000000287949*Math.pow(humid, 4)-0.0000000272164*Math.pow(humid, 3)+0.000000540335*Math.pow(humid, 2)-0.000509242*humid+1.03893;
		double  lb_c35=0.000000000000534447*Math.pow(humid, 5)-0.000000000162577*Math.pow(humid, 4)+0.0000000183504*Math.pow(humid, 3)-0.00000121048*Math.pow(humid, 2)-0.000168153*humid+1.015;
		double  lb_c15=0.000000000000177309*Math.pow(humid, 5)-0.0000000000382157*Math.pow(humid, 4)+0.0000000027252*Math.pow(humid, 3)-0.000000101171*Math.pow(humid, 2)+0.000064099*humid+0.995517;
		double  lb_c_01=-0.00000000000347267*Math.pow(humid, 5)+0.000000000876648*Math.pow(humid, 4)-0.00000007606*Math.pow(humid, 3)+0.0000024693*Math.pow(humid, 2)+0.0000144477*humid+0.997679;
        double cor_h = 0;
        if(tmp < 15){
     	  cor_h = ((tmp+1)*lb_c15+(15-tmp)*lb_c_01)/(15+1);
        }else
         if(tmp >= 15 && tmp < 35){
      	  cor_h = ((tmp-15)*lb_c35+(35-tmp)*lb_c15)/(35-15);
         }else{
      	  cor_h = ((tmp-35)*lb_c46+(46-tmp)*lb_c35)/(46-35);
         }
		return cor_h;
	}
//--------------------------
	static double tmp_em_p_g(double tmp_em, double tmp){
		
		  double t35c = 0;
          double t46c = 0;
          double t_01c = 0; 

       if(tmp_em < 14){
      	t35c = (-4.0226369*Math.pow(tmp_em, 3)+132.811395*Math.pow(tmp_em, 2)-1358.1386*tmp_em+4860.8606)/1000;
      }else if(tmp_em > 14 && tmp_em < 19){
      	t35c = (-3.3932291*Math.pow(tmp_em, 4)+221.9002*Math.pow(tmp_em, 3)-5414.9441*Math.pow(tmp_em, 2)+58440.153*tmp_em-234533.13)/1000;
      }else{
      	t35c =(-0.28764502*Math.pow(tmp_em, 4)+24.954483*Math.pow(tmp_em, 3)-821.84255*Math.pow(tmp_em, 2)+11944.602*tmp_em-63097.468)/1000;
      }

     if(tmp_em < 14){
  	    t46c = (-2.5068667*Math.pow(tmp_em, 3)+79.276373*Math.pow(tmp_em, 2)-750.38642*tmp_em+2776.946)/1000;
      }else if(tmp_em > 14 && tmp_em < 19){
      	t46c = (-3.3672809*Math.pow(tmp_em, 4)+213.28255*Math.pow(tmp_em, 3)-5039.1235*Math.pow(tmp_em, 2)+52637.619*tmp_em-204217.44)/1000;
      }else{
      	t46c = (-0.018444797*Math.pow(tmp_em, 4)+0.16446268*Math.pow(tmp_em, 3)+29.9155*Math.pow(tmp_em, 2)-998.6334*tmp_em+10303.86)/1000;
      }

    if(tmp_em < 14){
  	    t_01c = (-4.5159607*Math.pow(tmp_em, 3)+151.55618*Math.pow(tmp_em, 2)-1583.5243*tmp_em+5609.3031)/1000;
      }else if(tmp_em > 14 && tmp_em < 19){
      	t_01c = (-1.4327425*Math.pow(tmp_em, 4)+98.781648*Math.pow(tmp_em, 3)-2536.5239*Math.pow(tmp_em, 2)+28744.653*tmp_em-120529.89)/1000;
      }else{
      	t_01c = (-1.0264656*Math.pow(tmp_em, 4)+94.105876*Math.pow(tmp_em, 3)-3239.6823*Math.pow(tmp_em, 2)+49367.084*tmp_em-279390.03)/1000;
      }
        double cor_t_em = 0;
      
        if(tmp < 35){
      	 cor_t_em = ((tmp-1)*t35c+(35-tmp)*t_01c)/(35-1);
        }else{
         cor_t_em = ((tmp-35)*t46c+(46-tmp)*t35c)/(46-35);
        }
        
		return cor_t_em;
	}
//--------------------------
	static double freq_p_g(double freq, double tmp){	
		double cor_c46_f = (5.6936235804E-2*Math.pow(freq, 4)-1.1398060667E+1*Math.pow(freq, 3)+8.5557671747E+2*Math.pow(freq, 2)-2.8540204233E+4*freq+3.5697552760E+5);
        double cor_c35_f = (-2.3258473107E-3*Math.pow(freq, 4)+ 4.6611910254E-1*Math.pow(freq, 3)-3.5042131626E+1*Math.pow(freq, 2)+1.1713164670E+3*freq-1.4687836409E+4);
        double cor_c15_f = (2.5390159175E-3*Math.pow(freq, 4)-5.0734362982E-1*Math.pow(freq, 3)+3.8005271617E+1*Math.pow(freq, 2)-1.2649359968E+3*freq+1.5783725042E+4);
        double cor_c_01_f = (9.0209711315E-3*Math.pow(freq, 4)-1.8050201998*Math.pow(freq, 3)+1.3542341522E+2*Math.pow(freq, 2)-4.5151683779E+3*freq+5.6447336239E+4);
       
       
        double cor_f = 0;
        if(tmp < 15){
     	  cor_f = ((tmp+1)*cor_c15_f+(15-tmp)*cor_c_01_f)/(15+1);
         }else
          if(tmp >= 15 && tmp < 35){
       	  cor_f = ((tmp-15)*cor_c35_f+(35-tmp)*cor_c15_f)/(35-15);
          }else{
       	  cor_f = ((tmp-35)*cor_c46_f + (46-tmp)*cor_c35_f)/(46-35);
          }
        return cor_f;
	}
//---------------------------
	static double wobb_p_g(double wobb, double Gco2){
		double cor_co2_18 = 0.0000184742*Math.pow(wobb, 2)-0.00300223*wobb+1.10661;
        //  C02 = 0.18%: y = 1.84742E-Q5x2 • 3.00223E·03x + 1.10661E+OO
         double cor_co2_24 = 0.0000196821*Math.pow(wobb, 2)-0.00312828*wobb+1.10988;
        //  C02 = 0.24%: y = 1.96821E·05x2 · 3.12828E-03x + 1.10988E+OO 
         double cor_co2_30 = 0.0000199172*Math.pow(wobb, 2)-0.00315189*wobb+1.11046;
        //  C02 = 0.30%: y = 1.99172E·05x2 • 3.15189E·03x + 1.11046E+OO
         
         double cor_wco2 = 0;
         
         if(Gco2 > 0.24){
      	   cor_wco2 = ((Gco2-0.24)*cor_co2_24+(0.3-Gco2)*cor_co2_30)/(0.3-0.24);
         }else{
      	   cor_wco2 = ((Gco2-0.18)*cor_co2_18+(0.24-Gco2)*cor_co2_24)/(0.24-0.18);
         }
      return cor_wco2;
	}
//-----------------------------
	static double perte_altern_p_g(double fact_puis,double rend_altern){
		double pf09 = 0.0118*Math.pow(rend_altern, 2)+0.4246*rend_altern+1835;
        double pf095= 0.0102*Math.pow(rend_altern, 2)+0.3023*rend_altern+1830.9;
        double pf1  =0.0088*Math.pow(rend_altern, 2)-0.1165*rend_altern+1831.8;
        
        double corr_p_alt = 0;
        if(fact_puis > 0.95){
     	   corr_p_alt = ((fact_puis-0.95)*pf095+(1-fact_puis)*pf1)/(1-0.95);
        }else{
     	   corr_p_alt = ((fact_puis-0.9)*pf09+(0.95-fact_puis)*pf095)/(0.95-0.9);
        }
        return corr_p_alt;
	}
//-----------------------------	
	static double inj_p_g(double inj_eau){
		 double corr_inj_eau = -3.354598E-17*Math.pow(inj_eau, 3)+0.000000000005425096*Math.pow(inj_eau, 2)+0.00000090741543*inj_eau+0.92012674;
		 return corr_inj_eau;
	}
	
	static double hrf_p_g(double hdf){
	    double corr_h_d_f = 1-(0.05885*589.157+4.509545*Math.pow((hdf+18), 0.703598))/(589.157+Math.pow((hdf+18), 0.703598))/100;
		 return corr_h_d_f;
	}
//--------------------------------------------------------	
	//----------------Puissance Fuel Oil------------------
//--------------------------------------------------------		
static double tmp_amb_p_fo(double tmp){
		
	 double cor_t = 0;
      if(tmp >-1 && tmp <= -0.2){
   		 cor_t = 2.88477E-3*Math.pow(tmp, 4) + 7.50206E-3*Math.pow(tmp, 3) + 6.81122E-3*Math.pow(tmp, 2) + 3.90296E-4*tmp + 1.12177;
      }else if(tmp > -0.2 && tmp <= 23.1){
   		 cor_t =-1.41982E-7*Math.pow(tmp, 4)+4.99806E-6*Math.pow(tmp, 3)-7.82496E-5*Math.pow(tmp, 2)-1.73676E-3*tmp+1.12956;
      }else if(tmp > 23.1 && tmp <= 36.7){
   		 cor_t = -7.43169e-8*Math.pow(tmp, 4)+8.03445e-6*Math.pow(tmp, 3)-3.50413e-4*Math.pow(tmp, 2)+1.54443e-3*tmp+1.14225;
      }else if(tmp > 36.7 && tmp <= 46){
   		 cor_t = -7.07015e-7*Math.pow(tmp, 4)+1.20028e-4*Math.pow(tmp, 3)-7.64286e-3*Math.pow(tmp, 2)+2.08746e-1*tmp-1.02806;
      }
		
		return cor_t;
	}
  //-----------------	
	static double pression_p_fo(double press){
		 
		press = press * 1000 / 750.06375541921;
		
		
        double cor_p = 1.40190E-1*Math.pow((press/1000),2)+6.37075E-1*(press/1000)+2.10784E-1;
         
		return cor_p;
	}
//-------------------------	
	static double humidite_p_fo(double humid, double tmp){
		 
		double lb_c46=3.20590E-12*Math.pow(humid, 4)+2.17176E-10*Math.pow(humid, 3)- 4.81196E-7*Math.pow(humid, 2)- 1.92978E-4*humid+1.01648;
		double lb_c35=3.33952E-11*Math.pow(humid, 4)- 8.86360E-9*Math.pow(humid, 3)+3.64898E-7*Math.pow(humid, 2)-3.42381E-5*humid+1.00305;
		double lb_c15=1.13370E-11*Math.pow(humid, 4)- 2.28739E-9*Math.pow(humid, 3)+1.44307E-7*Math.pow(humid, 2)+3.36151E-5*humid+9.97345E-1;
		double lb_c_01=-3.63804E-12*Math.pow(humid, 4)-8.12779E-10*Math.pow(humid, 3)-5.11907E-8*Math.pow(humid, 2)+1.58111E-5*humid+9.98906E-1;
        double cor_h = 0;
          
        if(tmp < 15){
     	  cor_h = ((tmp+1)*lb_c15+(15-tmp)*lb_c_01)/(15+1);
        }else
         if(tmp >= 15 && tmp < 35){
      	  cor_h = ((tmp-15)*lb_c35+(35-tmp)*lb_c15)/(35-15);
         }else{
      	  cor_h = ((tmp-35)*lb_c46+(46-tmp)*lb_c35)/(46-35);
      	 
         }
		return cor_h;
	}
//--------------------------
	static double tmp_em_p_fo(double tmp_em, double tmp){
		 double cor_t_em = 0;
		 
		double t_01c,t35c,t46c = 0;
			 
			 if(tmp_em <=16){
				 t_01c = -7.29167E-1 * Math.pow(tmp_em, 3)+2.06250E+1*Math.pow(tmp_em, 2)-5.83331E+1*tmp_em-5.40000E+2;
			 }else{
				 t_01c = -1.91502E-02 * Math.pow(tmp_em, 5)+1.75990*Math.pow(tmp_em, 4)-6.21421E+1*Math.pow(tmp_em, 3)+1.01974E+3*Math.pow(tmp_em, 2)-7.37799E+3*tmp_em+1.70930E+4;
			 }
		
			 if(tmp_em <=16){
				 t35c = -2.08333E-1*Math.pow(tmp_em, 4)+9.99999*Math.pow(tmp_em, 3) - 1.81666E+2*Math.pow(tmp_em, 2)+1.58500E+3*tmp_em - 5.41999E+3;
			 }else{
				 t35c =2.80003E-2 * Math.pow(tmp_em, 5)-3.09429*Math.pow(tmp_em, 4)+1.35356E+2*Math.pow(tmp_em, 3)-2.94461E+3*Math.pow(tmp_em, 2)+3.18484E+4*tmp_em-1.36006E+5;
			 }
		 
			 if(tmp_em <=16){
				 t46c = -5.20833E-2 * Math.pow(tmp_em, 3)-5.93750*Math.pow(tmp_em, 2)+2.59583E+2*tmp_em-1.64000E+03;   
			 }else{
				 t46c = 3.13262E-2 * Math.pow(tmp_em, 5)-3.43522*Math.pow(tmp_em, 4)+1.49092E+2*Math.pow(tmp_em, 3)-3.21771E+3*Math.pow(tmp_em, 2)+3.45346E+04*tmp_em-1.46439E+05;
			 }
		 	
		 
		 
		 if(tmp < 35){
	      	 cor_t_em = ((tmp-1)*t35c+(35-tmp)*t_01c)/(35-1);
	        }else{
	         cor_t_em = ((tmp-35)*t46c+(46-tmp)*t35c)/(46-35);
	        }
		 
	
		return cor_t_em/1000;
	}
//--------------------------
	static double freq_p_fo(double freq, double tmp){	
		double cor_c46_f = 0;
        if(freq <= 50){
	            cor_c46_f = (-8.0402010E-3*Math.pow(freq, 2) + 8.9636059E-1*freq - 2.3717527E+1);
        }else{
	            cor_c46_f = (-5.4210446E-3*Math.pow(freq, 2) + 5.9853815E-1*freq - 1.5374296E+01);
        }
        double cor_c35_f = (2.2957612E-3*Math.pow(freq, 3)- 3.5701228E-1*Math.pow(freq, 2)+1.8522313E+1*freq-3.1955509E+2);
        double cor_c15_f = 0;
        if(freq <= 49.05){
     	   cor_c15_f = (-5.4944269E-3*Math.pow(freq, 3) + 8.1031165E-1*Math.pow(freq, 2) - 3.9804898E+1*freq + 6.5226910E+2);
        }else{
     	   cor_c15_f = (1.0853991E-3*Math.pow(freq, 3)- 1.6806338E-1*Math.pow(freq, 2)+ 8.6680976*freq - 1.4792132E+2);
        }
        double cor_c_01_f = (4.9711672E-4*Math.pow(freq, 3)-7.6333735E-2*Math.pow(freq, 2)+3.8993558*freq - 6.5273043E+1);
        double cor_f = 0;
        if(tmp < 15){
     	    cor_f = ((tmp+1)*cor_c15_f+(15-tmp)*cor_c_01_f)/(15+1);
         }else
          if(tmp >= 15 && tmp < 35){
       	  cor_f = ((tmp-15)*cor_c35_f+(35-tmp)*cor_c15_f)/(35-15);
          }else{
       	  cor_f = ((tmp-35)*cor_c46_f + (46-tmp)*cor_c35_f)/(46-35);
          }
        return cor_f;
	}
//---------------------------
	static double inj_p_fo(double inj_eau){
        double corr_inj_eau = -3.354598E-17*Math.pow(inj_eau, 3)+5.4250960E-12*Math.pow(inj_eau, 2)+9.0741543E-7*inj_eau+9.2012674E-1;
		 return corr_inj_eau;
	}
//---------------------------
	static double pci_p_fo(double pci){
        double corr_pci  = -6.1092572E-18*Math.pow(pci, 4) + 1.1811078E-12*Math.pow(pci, 3) - 8.4508187E-8*Math.pow(pci, 2) + 2.6617697E-3*pci - 3.0222428E+1;
                     //y = -6.1092572E-18x4                + 1.1811078E-12x3                - 8.4508187E-8x2                + 2.6617697E-3x    - 3.0222428E+01		
        return corr_pci;
	}
//---------------------------	
	static double hdf_p_fo(double hdf){
        double corr_hdf  = 1-(-0.05885*585.157+4.509545*Math.pow((hdf+18), 0.703598))/(589.157+Math.pow((hdf+18), 0.703598))/100;
		return corr_hdf;
	}
//--------------------------------------------------------	
	//----------------consommation Gaz------------------
//--------------------------------------------------------
	
static double tmp_amb_c_g(double tmp){
		
	double cor_t = 0;
     if(tmp >-1 && tmp <= 5){
  		 cor_t = -1.56625E-7*Math.pow(tmp, 5)+2.91103E-6*Math.pow(tmp, 4)-1.79363E-5*Math.pow(tmp, 3)+1.68318E-5*Math.pow(tmp, 2)-4.86654E-3*tmp+ 1.19270;
  		 // -1e< T < 4.9"C: y= -1.56625E-07x5 + 2.91103E-06x4- 1.79363E-05x3 + 1.68318E-05x2 - 4.86654E-03x + 1.19270E+OO
     }else if(tmp > 5 && tmp < 29.9){
  		 cor_t = -7.94350E-09*Math.pow(tmp, 5) + 7.39634E-07*Math.pow(tmp, 4)- 2.45926E-5*Math.pow(tmp, 3) + 3.30547E-4*Math.pow(tmp, 2)-6.94372E-3*tmp+1.21303;
  		// 5c < T < 29.9·c: y= -7.94350E-09x5 + 7.39634E-07x4- 2.45926E-05x3 + 3.30547E-04x2- 6.94372E-03x + 1.21303E+OO
     }else if(tmp >= 29.9 && tmp < 46){
  		 cor_t = -2.56218E-7*Math.pow(tmp, 5) + 4.87089E-5*Math.pow(tmp, 4)-3.67995E-3*Math.pow(tmp, 3)+1.38035E-1*Math.pow(tmp, 2)-2.57898*tmp+20.3119;
  		// 29.9"C < T < 46"C: y= -2.56218E-07x5 + 4.87089E-05x4- 3.67995E-03x3 + 1.38035E-01x2 - 2.57898E+00x + 2.03119E+Ol  
     }
		
		return cor_t;
	}
  //-----------------	
	static double pression_c_g(double press){ 
		press = press * 1000 / 750.06375541921;
        double cor_p = -9.26300e-3*Math.pow(press/1000, 2)+1.01583*(press/1000)-1.95289E-2;
		return cor_p;
	}
//-------------------------	
	static double humidite_c_g(double humid, double tmp){
		 
		double lb_c46=5.05709E-11*Math.pow(humid, 4) - 8.06172E-9*Math.pow(humid, 3) - 8.45916E-8*Math.pow(humid, 2) - 3.41436E-04*humid + 1.02707;
        //  T 46' C: y = 5.05709E-llx4- 8.06172E-09x3 - 8.45916E-08x2 - 3.41436E-04x + l.02707E+OO
		double lb_c_01=3.63045E-11*Math.pow(humid, 4) - 5.56592E-9*Math.pow(humid, 3)+1.00546E-7*Math.pow(humid, 2)+ 3.85820E-05*humid+ 9.97761E-1;
        //  T -1 ' C: y = 3.63045E-llx4 - 5.56592E-09x3+ l.OOS46E-07x2 + 3.85820E-0Sx + 9.97761E-Ol
		double lb_c15=1.88693E-12*Math.pow(humid, 4)- 3.85295E-10*Math.pow(humid, 3)- 1.62871E-9*Math.pow(humid, 2) + 6.21130E-05*humid+9.95571E-1;
        //  T lS' C : y = 1.88693E-12x4 - 3.85295E-10x3- l.62871E-09x2 + 6.21130E-05x + 9.95571E-01
		double lb_c35=-1.48139E-11*Math.pow(humid, 4)+ 2.87827E-9*Math.pow(humid, 3)- 4.02245E-7*Math.pow(humid, 2)- 1.10076E-4*humid+1.00948;
       //  T 3S' C: y = -l.48139E-llx4 + 2.87827E-09x3- 4.02245E-07x2- l.l0076E-04x + l.00948E+OO
      double cor_h = 0;
      if(tmp < 15){
   	  cor_h = ((tmp+1)*lb_c15+(15-tmp)*lb_c_01)/(15+1);
      }else
       if(tmp >= 15 && tmp < 35){
    	  cor_h = ((tmp-15)*lb_c35+(35-tmp)*lb_c15)/(35-15);
       }else{
    	  cor_h = ((tmp-35)*lb_c46+(46-tmp)*lb_c35)/(46-35);
       }
       if(tmp < 15){
     	  cor_h = ((tmp+1)*lb_c15+(15-tmp)*lb_c_01)/(15+1);
       }else
         if(tmp >= 15 && tmp < 35){
      	  cor_h = ((tmp-15)*lb_c35+(35-tmp)*lb_c15)/(35-15);
         }else{
      	  cor_h = ((tmp-35)*lb_c46+(46-tmp)*lb_c35)/(46-35);
         }
		return cor_h;
	}
//--------------------------
	static double freq_c_g(double freq, double tmp){	
		 		double cor_c46_f = (4.174488479E-2*Math.pow(freq, 4) - 8.356329497*Math.pow(freq, 3) + 6.272094475E+2*Math.pow(freq, 2) - 2.092080854E+4*freq + 2.616534655E+05);
	          // T 46•c : y = 4.174488479E-02x4 - 8.356329497E+00x3 + 6.272094475E+02x2- 2.092080854E+04x + 2.616534655E+05
	           double cor_c35_f = (-2.272494378E-3*Math.pow(freq, 4)+ 4.558331934E-1*Math.pow(freq, 3)- 3.429706427E+1*Math.pow(freq, 2) + 1.147267549E+3*freq - 1.439577610E+4);
	         // T 35•c: y= -2.272494378E-03x4 + 4.558331934E-Olx3 - 3.429706427E+Olx2 + 1.147267549E+03x- 1.439577610E+04
	           double cor_c15_f = (2.232426711E-3*Math.pow(freq, 4) - 4.464396271E-01*Math.pow(freq, 3) + 3.347036761E+01*Math.pow(freq, 2) - 1.114931190E+03*freq + 1.392392690E+04);
	         // T 15°c: y= 2.232426711E-03x4- 4.464396271E-Olx3 + 3.347036761E+Ol x2- 1.114931190E+03x + 1.392392690E+04
	           double cor_c_01_f = (8.028657662E-3*Math.pow(freq, 4)-1.606424388*Math.pow(freq, 3)+1.205204457E+2*Math.pow(freq, 2)-4.018181549E+3*freq+5.023290125E+4);
	         // T -1 •c: y= 8.028657662E-03x4 - 1.606424388E+00x3+ 1.205204457E+02x2- 4.018181549E+03x + 5.023290125E+04 
	           double cor_f = 0;
	           if(tmp < 15){
	        	  cor_f = ((tmp+1)*cor_c15_f+(15-tmp)*cor_c_01_f)/(15+1);
             }else
	           if(tmp >= 15 && tmp < 35){
	        	  cor_f = ((tmp-15)*cor_c35_f+(35-tmp)*cor_c15_f)/(35-15);
	           }else{
	        	  cor_f = ((tmp-35)*cor_c46_f + (46-tmp)*cor_c35_f)/(46-35);
	           }
        return cor_f;
	}
//---------------------------
	static double wobb_c_g(double wobb, double Gco2){
		 double cor_co2_18 = 1.63075E-5*Math.pow(wobb, 2) - 2.48412E-3*wobb + 1.08540;
         //  C02 = 0.18%: y= 1.63075E-05x2 - 2.48412E-03x + 1.08540E+OO
          double cor_co2_24 = 1.64174E-05*Math.pow(wobb, 2) - 2.49526E-03*wobb + 1.08567;
         //  C02 = 0.24%: y= 1.64174E-05x2 - 2.49526E-03x + 1.08567E+OO 
          double cor_co2_30 = 1.64671E-5*Math.pow(wobb, 2) - 2.50017E-3*wobb + 1.08579;
         //  C02 = 0.30%: y= 1.64671E-05x2 - 2.50017E-03x + 1.08579E+OO
          
          double cor_wco2 = 0;
          
          if(Gco2 > 0.24){
       	   cor_wco2 = ((Gco2-0.24)*cor_co2_24+(0.3-Gco2)*cor_co2_30)/(0.3-0.24);
          }else{
       	   cor_wco2 = ((Gco2-0.18)*cor_co2_18+(0.24-Gco2)*cor_co2_24)/(0.24-0.18);
          }
      return cor_wco2;
	}
	
	static double hdf_c_g(double hdf){
        double corr_hdf  = 1-(-0.05885*585.157+4.509545*Math.pow((hdf+18), 0.703598))/(589.157+Math.pow((hdf+18), 0.703598))/100;
		return corr_hdf;
	}

//--------------------------------------------------------	
	//----------------consommation Fuel Oil------------------
//--------------------------------------------------------
	static double tmp_amb_c_fo(double tmp){
		
		double cor_t = 0;
	   if(tmp <= -0.2 && tmp >-1){
		   cor_t = 2.51235E-4*Math.pow(tmp, 4)+ 7.86182E-4*Math.pow(tmp, 3)+ 8.99731E-4*Math.pow(tmp, 2)-2.32994E-3*tmp + 1.12737;
	   }else  if(tmp <= 23.1 && tmp >-0.2){
		   cor_t = -1.08161E-7*Math.pow(tmp, 4)+ 3.76817e-6*Math.pow(tmp, 3) - 5.43340e-5*Math.pow(tmp, 2)- 2.51633e-3*tmp + 1.13417;
	   }else  if(tmp <= 36.7 && tmp >23.1){
		   cor_t = -8.28603e-8*Math.pow(tmp, 4)+ 9.49464e-6*Math.pow(tmp, 3) - 4.25039e-4*Math.pow(tmp, 2)+ 3.52396e-3*tmp + 1.11459;
	   } else  if(tmp <= 46 && tmp >36.7){
		   cor_t = -5.99612e-7*Math.pow(tmp, 4)+ 1.00631e-4*Math.pow(tmp, 3) - 6.33564e-3*Math.pow(tmp, 2)+ 1.70923e-1*tmp - 6.35527e-1;
	   } 
			
			return cor_t;
		}
	  //-----------------	
		static double pression_c_fo(double press){
			press = press * 1000 / 750.06375541921;
		 double cor_p = -2.86092E-2*Math.pow(press/1000, 2) + 9.15525E-1*(press/1000)+ 1.01931E-1;
			return cor_p;
		}
	//-------------------------	
		static double humidite_c_fo(double humid, double tmp){

			double lb_c_01=-6.02852E-14*Math.pow(humid, 4) + 1.22458E-11*Math.pow(humid, 3)-9.25329E-10*Math.pow(humid, 2)+ 1.50382E-5*humid+ 9.98904E-1;
			double lb_c15=3.26140E-13*Math.pow(humid, 4)- 6.41554E-11*Math.pow(humid, 3)-4.71566E-9*Math.pow(humid, 2) + 3.77470E-5*humid+9.97285E-1;
			double lb_c35=2.77269E-11*Math.pow(humid, 4)- 7.23075E-9*Math.pow(humid, 3)+3.56257E-7*Math.pow(humid, 2)- 2.83705E-6*humid+1.00033;
			double lb_c46=2.90193E-11*Math.pow(humid, 4) - 3.50040E-9*Math.pow(humid, 3) - 2.63941E-7*Math.pow(humid, 2) - 9.46363E-5*humid + 1.00885;

			double cor_h = 0;
	      if(tmp < 15){
	   	  cor_h = ((tmp+1)*lb_c15+(15-tmp)*lb_c_01)/(15+1);
	      }else
	       if(tmp >= 15 && tmp < 35){
	    	  cor_h = ((tmp-15)*lb_c35+(35-tmp)*lb_c15)/(35-15);
	       }else{
	    	  cor_h = ((tmp-35)*lb_c46+(46-tmp)*lb_c35)/(46-35);
	       }
	       if(tmp < 15){
	     	  cor_h = ((tmp+1)*lb_c15+(15-tmp)*lb_c_01)/(15+1);
	       }else
	         if(tmp >= 15 && tmp < 35){
	      	  cor_h = ((tmp-15)*lb_c35+(35-tmp)*lb_c15)/(35-15);
	         }else{
	      	  cor_h = ((tmp-35)*lb_c46+(46-tmp)*lb_c35)/(46-35);
	         }
			return cor_h;
		}
	//--------------------------
		static double freq_c_fo(double freq, double tmp){	
			double cor_c46_f = 0;
	        if(freq <= 50){
		            cor_c46_f = (-2.7510067E-3*Math.pow(freq, 2) + 3.4843656E-1*freq - 9.5443116);
	        }else{
		            cor_c46_f = (-4.2998528E-3*Math.pow(freq, 2) + 4.7697199E-1*freq - 1.2098968E+1);
	        }
	        double cor_c35_f = (1.9240377E-3*Math.pow(freq, 3)- 2.9782912E-1*Math.pow(freq, 2)+1.5386680E+1*freq-2.6426592E+2);
	        double cor_c15_f = 0;
	        if(freq <= 49.05){
	     	   cor_c15_f = (5.5082776E-3*Math.pow(freq, 3) - 8.1700380E-1*Math.pow(freq, 2) +4.0416770E+1*freq + 6.6586366E+2);
	        }else{
	     	   cor_c15_f = (1.1629923E-3*Math.pow(freq, 3)- 1.7893423E-1*Math.pow(freq, 2)+ 9.1766832*freq - 1.5587262E+2);
	        }
	        double cor_c_01_f = (4.4213418E-4*Math.pow(freq, 3)-6.7824515E-2*Math.pow(freq, 2)+3.4658376*freq - 5.7997366E+1);
	        double cor_f = 0;
	        if(tmp < 15){
	     	    cor_f = ((tmp+1)*cor_c15_f+(15-tmp)*cor_c_01_f)/(15+1);
	         }else
	          if(tmp >= 15 && tmp < 35){
	       	  cor_f = ((tmp-15)*cor_c35_f+(35-tmp)*cor_c15_f)/(35-15);
	          }else{
	       	  cor_f = ((tmp-35)*cor_c46_f + (46-tmp)*cor_c35_f)/(46-35);
	          }
	        return cor_f;
		}
	//---------------------------
		static double pci_c_fo(double pci){
			 double cor_pci = -6.5280093E-19*Math.pow(pci, 4)+1.9685847E-13*Math.pow(pci, 3)-1.8062907E-8*Math.pow(pci, 2)+6.7085598E-4*pci - 7.8696321;
	      return cor_pci;
		}
	//---------------------------
		static double inj_c_fo(double inj){
			 double cor_inj = -1.7379613E-17*Math.pow(inj, 3)+2.6721193E-12*Math.pow(inj, 2)+1.7650826E-6*inj + 8.6726000E-1;
	      return cor_inj;
		}
	//----------------------------
		static double hdf_c_fo(double hdf){
	        double corr_hdf  = 1-(-0.05885*585.157+4.509545*Math.pow((hdf+18), 0.703598))/(589.157+Math.pow((hdf+18), 0.703598))/100;
			return corr_hdf;
		}
	
//-------------Correction puissance Gaz-----------------
	static double corr_puissance_g(double tmp, double press, double humid, double tmp_em, double freq,double wobb,double Gco2, double fact_puis, double rend_altern, double hdf, double puis_auxi){
		  //-----------Correction température-----------------  
    	double cor_t = fonction.tmp_amb_p_g(tmp);
    	double val_cor_tmp = 1/cor_t;
           //-----------Correction pression-----------------  
        double cor_p = fonction.pression_p_g(press);
        double val_cor_press = 1/cor_p;
           //-----------Correction humidité-----------------  
        double cor_h = fonction.humidite_p_g(humid, tmp);
        double val_cor_humid = 1/cor_h;
           //-----------Correction température eau de mer----------------  
        double cor_t_em = fonction.tmp_em_p_g(tmp_em, tmp);
        double val_cor_tem = 0 - cor_t_em;
           //-----------Correction frequence-----------------  
        double cor_f = fonction.freq_p_g(freq, tmp);
        double val_cor_freq = 1/cor_f;
           //-----------Correction wobb-----------------  
        double cor_wco2 = fonction.wobb_p_g(wobb, Gco2);
        double val_cor_wco2 = 1/cor_wco2;
       //-----------Correction perte alternateur-----------------  
       double corr_p_alt = fonction.perte_altern_p_g(fact_puis, rend_altern);
       double val_nom_p_alt = fonction.perte_altern_p_g(0.9, rend_altern);
       double val_cor_p_alt = val_nom_p_alt - corr_p_alt;
       //-----------Correction injection d'eau-----------------  
        double corr_inj_eau = /*fonction.inj_p_g(inj_eau)*/ 1;
        double val_cor_inj = 1/corr_inj_eau;
       //-----------Correction heure de flame-----------------  
	    double corr_h_d_f = fonction.hrf_p_g(hdf);
	    double val_cor_hdf =1+ (0.998 - corr_h_d_f);
      
       
       
double tphh2 = Math.abs(((rend_altern*1000)-val_cor_p_alt-val_cor_tem + puis_auxi)*(val_cor_tmp*val_cor_press*val_cor_humid*val_cor_wco2*val_cor_freq*val_cor_inj)*val_cor_hdf);

		         // ABS(((B39*1000)-E45-E27+E53)*(E14*E16*E22*E39*E33*E49)*E61)
     return tphh2;
	}
	
	//-------------Correction puissance Fuel-----------------	
	static double corr_puissance_fo(double tmp, double press, double humid, double tmp_em, double freq,double inj_eau,double pci, double fact_puis, double rend_altern, double hdf, double puis_auxi){

		//-----------Correction température ambiante-----------------
	        double cor_t = fonction.tmp_amb_p_fo(tmp);
	        double val_cor_tmp = 1/cor_t;
    //-----------Correction pression ambiante-----------------
        double cor_p = fonction.pression_p_fo(press);
        double val_cor_press = 1/cor_p;
      //-----------Correction humidité-----------------   
         double cor_h = fonction.humidite_p_fo(humid, tmp);
         double val_cor_humid = 1/cor_h;
      //-----------Correction température eau de mer -----------------         
         double cor_t_em = fonction.tmp_em_p_fo(tmp_em, tmp);
         double val_cor_tem = 0 - cor_t_em;
    //-----------Correction fréquence-----------------  
        double cor_f = fonction.freq_p_fo(freq, tmp);
        double val_cor_freq = 1/cor_f;
    //-----------Correction injection d'eau-----------------  
       double corr_inj_eau = fonction.inj_p_fo(inj_eau);
       double val_cor_inj = 1/corr_inj_eau;
     //-----------Correction perte alternateur-----------------  
       double corr_perte_altern = fonction.perte_altern_p_g(fact_puis, rend_altern);
       double nominal_corr_p_altrn= fonction.perte_altern_p_g(0.9, rend_altern);
       double val_cor_p_alt = nominal_corr_p_altrn - corr_perte_altern;
    //-----------Correction PCI-----------------------------
       double corr_pci  = fonction.pci_p_fo(pci);
       double val_cor_pci = 1/corr_pci;
       System.out.println("pci : "+pci+"   Correction : "+corr_pci);
       //-----------Correction heure de flame-----------------  
	    double corr_h_d_f = fonction.hrf_p_g(hdf);
	    double val_cor_hdf =1+ (corr_h_d_f - 0.998);
        //-----------Correction Global-----------------        
        double tphh2 = Math.abs(((rend_altern*1000)-val_cor_p_alt-val_cor_tem + puis_auxi)*(val_cor_tmp*val_cor_press*val_cor_humid*val_cor_pci*val_cor_freq*val_cor_inj)*val_cor_hdf);
       
     return tphh2;
	}
}
