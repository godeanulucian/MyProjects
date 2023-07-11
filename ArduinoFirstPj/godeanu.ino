//	7 SEGMENTE_lab3
void initiale()
{
  //afisare litera L (LUCIAN), setare multipla de biti
 PORTD|=(1<<5)|(1<<6);
 PORTB|=(1<<0);
 	_delay_ms(1000);
 //resetare multipla de biti, oprire afisare
 PORTD&=~((1<<5)|(1<<6));
 PORTB&=~(1<<0);
	 _delay_ms(250);
  //afisare litrea F (FLORIN)
 PORTD|=(1<<6)|(1<<7);
 PORTB|=(1<<0)|(1<<1);
 	_delay_ms(1000);
 //resetare biti, oprire afisare
 PORTD&=~((1<<6)|(1<<7));
 PORTB&=~((1<<0)|(1<<1));
  _delay_ms(250);
  //afisare litrea G (GODEANU)
 PORTD|=(1<<4)|(1<<5)|(1<<7);
 PORTB|=(1<<0)|(1<<1)|(1<<2);
 	_delay_ms(1000);
 //resetare biti, oprire afisare
 PORTD&=~((1<<4)|(1<<5)|(1<<7));
 PORTB&=~((1<<0)|(1<<1)|(1<<2));
  _delay_ms(250);
}


//	TIMERE_lab5_prelucrat dupa tabelele cu registrii si bitii acestora si dupa materialele pt proiect
//TC1 - 16 biti (0-65535)
void timer1()
{
 cli(); //clear all interrupts (globally)
 TCCR1A=0; //setarea valorilor registrului de control pe 0
 TCCR1B=0; //setarea valorilor registrului de control pe 0
 TCNT1=0; //initializam valoarea counter-ului pe 0 (reg numarator)
 OCR1A=15624; //registru de comparare, valoare calculata
 TCCR1B|=(1<<WGM12); //selectam cu WGM12 pt modul de operare CTC (ideal pentru a genera intreruperi la intervale dorite de timp)
 TCCR1B|=(1<<CS12)|(1<<CS10); //activam TCCR si selectam valoarea prescaler=1024 (max) cu CS12 si CS10
 TIMSK1|=(1<<OCIE1A); //activam registru de control pt RTI, enable timer compare interrupt
 sei(); //set interrupt enable
}
//TC0/TC2 - 8 biti (0-255)
void timer2()
{
  TCCR2A = 0; //setarea valorilor registrului de control pe 0
  DDRD|=(1<<3); //se va aprinde ledul alb
  TCCR2A |= (1 << COM2B1); //clear OC2B on match
  TCCR2A |= (1 << WGM21)|(1 << WGM20); //fast pwm
  TCCR2B |= (1 << CS22) | (1 << CS21) | (1 << CS20); //prescaler
  OCR2B = 0;
}

//ADC_lab4_anexa
void adc_init()
{
 //setam registrii stare, control, cu admux selectam canalul de intrare de unde se preia informatia
//set division factor between system clock frequency and the input clock to the ADC‐ 128
ADCSRA |= ((1<<ADPS2) | (1<<ADPS1) | (1<<ADPS0)); //set division factor between system clock (biti specifici)
ADMUX |= (1<<REFS0); //AVcc with external capacitor at Aref pin (selectam tens de referinta)
ADCSRA |= (1<<ADEN); //enable ADC
ADCSRA |= (1<<ADSC); //pornire conversie ADC
}
uint16_t read_adc(uint8_t channel)
{
ADMUX &= 0xF0; // selectare input A0->A5
ADMUX |= channel; // selectare canal
ADCSRA |= (1<<ADSC); //pornire conversie
while(ADCSRA & (1<<ADSC));//asteptam pana se incheie operatia de conversie
return ADC; //returneaza tensiunea
}


//	TEMPERATURA_functie_lab_anexa
void temp()
{
  int n;
  float v,t;
  char intreg[5], frac[2];
  
  n=read_adc(5); //citire valoare port analog A5
  v=float(n*5)/1023; //calcul tensiune
  t=(float)100*(v-0.5); //calcul temperatura
  
  itoa((int)t, intreg, 10); //conversie numar in sir de caractere
  itoa((int)(abs((t-(int)t))*100), frac, 10); //conversie numar in sir de caractere
  //construire sir de caractere pentru afisare 
  strcat(intreg, ".");
  strcat(intreg, frac);
  strcat(intreg, "\n");
  
  if(t>=(35))
  {
    PORTB|=(1<<5); //setare - aprindere led pentru temperatura
  }
  else
      if(t<(35))
  {
    PORTB &=~(1<<5); //resetare - stingere led pentru temperatura
  }
  if(t<=125)
  {
    for(int i = 0; i < strlen(intreg); i++)
  {
     //verificam daca putem transmite date noi (TXB gol)
     while (!(UCSR0A & (1<<UDRE0))); //UDRE flag=1 in registrul UCSR0A
     UDR0 = intreg[i];//valoare intra in registrul buffer ce stocheaza RXB/TXB
  }
  }
}


//	COMUNICARE SERIALA
void USART_init()
{
  UBRR0 = 103; //setare viteza transmisie date 9600 biti/sec prin reg UBRR0
  UCSR0B = (1<<RXEN0)|(1<<TXEN0); //enable transmisie(txen0)/receptie(rxen0)
  UCSR0C = (1<<USBS0)|(3<<UCSZ00); //setare biti date si biti stop
}
void tastatura()
{
  unsigned char input;
  input=UDR0; //preluam valoarea introdusa de la tastatura
  if(input=='A')//verificam daca a fost introdus caracterul A
  {
     PORTB|=(1<<4);//aprindem ledul 
  }
  else if(input=='S')//verificam daca a fost introdus caracterul S
  {
     PORTB&=~(1<<4);//stingem ledul
  }
}


//	PWM
void PWM()
{
  //fade in
    for(int i=0; i<256; i++) //parcurgem factorul de umplere pana la maxim
    {
    	OCR2B =i;
   		 _delay_ms(3);
    }
   	OCR2B =255;
    _delay_ms(1000);
  //fade out
    for(int i=256; i>0; i--) //parcurgem factorul de umplere pana la minim
    {
    	OCR2B =i;
    	_delay_ms(3);
    }
    OCR2B =0;
    _delay_ms(1000);
}

void setup()
{
cli(); //oprire intreruperi
//setam pinii de intrare/iesire
DDRB = 0x3F; //0B00111111
DDRD = 0xF8; //0B11111000
DDRC = 0x00; //0B00
//initializam functiile create mai sus
timer1();
adc_init();
timer2();
USART_init();
sei(); //pornire intreruperi
}

//MAIN
void loop()
{
  initiale();
  temp();
  tastatura();
  PWM();
}

ISR(TIMER1_COMPA_vect)
{
  //trecem bitul in toggle pentru a aprinde si stinge ledul (XOR)
   PORTB ^=( 1 << 3);
}