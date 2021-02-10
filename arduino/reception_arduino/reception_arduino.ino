#include <Servo.h>

char c;

Servo servoRetrait;
Servo servoDepot;

int pinR = 9;
int pinD = 7;

void setup()
{                
    Serial.begin(9600);
    servoRetrait.attach(pinR);
    servoDepot.attach(pinD);
}

void loop()
{
     while (true) 
     {
          if (Serial.available() > 0)
          {
              c = Serial.read();
    
              //faire le dépôt
              if(c == '0')
              {
                //demi-rotation aller
                for(int i = 0; i < 180; i++)
                {
                  servoDepot.write(i);
                  delay(15);
                };
    
                //Attente entre les rotations
                delay(2000);
    
                //demi-rotation retour
                for(int i = 180; i >= 0; i--)
                {
                  servoDepot.write(i);
                  delay(15);
                };
              }
    
              //faire le retrait
              if(c == '1')
              {
                //Récupérer l'angle actuel
                int angle = servoRetrait.read();
                //Effectuer la rotation
                for(int i = 0; i < 180; i++)
                {
                  servoRetrait.write(i);
                  delay(15);
                };
              }
              
          }
     }
}
