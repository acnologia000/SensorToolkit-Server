# The current repo is dead, the project will be soon back with jetpack compose as UI toolkit, link to new repo will be provided soon 

# SensorToolkit-Server
A HTTP(not exactly HTTP but a custom http like protocol) rest api server for serving sensor 
data to micro controller boards with wireless capability or rather any device that can make
http call, parse/deserialise json and use sensor data


# Status
the project is work in progress, Expected to get first alpha working by Tuesday 31 March 2021


# Focus
to build a reliable method to access smartphone inputs in wireless/internet capable micro controllers
inputs/feataures include but not limited to 

- Aceelerometer 
- Gyroscope
- Gps
- Sending SMS
- Getting notification from micro controller 
- Basic Remote Control protocal(WiFi)( in future ) 
- Using smartphone as Bluetooth relay( in future )
- Off device processing (through Lua)( in future )

# Planned Features by version 
Apha:
- Accelerometers
- Gyroscope (Rotation Vector)

Beta:
- GPS
- SMS

Stable:
- Getting Notifications

Version 2:
- Basic Remote Protocol

Version 3:
- Bluetooth Connectivity 
- Off Device Processing

Version 4:

you can suggest some via opening issues or dropping a dm on my twitter/instagram 
(they have same handle [see that in my github profile]) 

# Panned micro conrollers / SBC  to support 
- ESP8266
- ESP32
- ESP32 S2
- Arduino Wireless capable boards
- Raspberry Pi (All except pico)
- Basically anything that can send http request and process it 
