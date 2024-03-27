Crescendo 2024

Introducing Team 6925's code for Crescendo.

Drivetrain: Our drivetrain consists of the SDS MK4i Swerve Drivetrain. We currently use 8 Krakens x60 controlled by Talon FXs to power both the drive and steer motors. In addition, we are running the L3 gear ratio with 16t. (Normal L3 comes with 14t) Our Drivetrain runs in field oriented mode however it can be toggled and switched to robot oriented with one of the buttons on the controller. We also have a speed controller in which when it is pressed, speed is multiplied by 0.5. 

Over the bumper intake: Our over the bumper intake is our main method of intaking gamepieces. It is very similar to RI3D Cranberry Alarm's design. It consists of two Falcon 500s controlled by Talon FXs. One is for the indexer and one is for the pivot. The intake is programmed to many different posistions that is activated using the manipulator controller (Logitech Gamepad). 

Shooter: Our shooter consists of two Kraken x60s each driving a shooter roller controlled by Talon FXs programmed to the manipulator controller. Both top and bottom shooter motors can be controlled separately allowing us to set them to different speeds to potentially "loft" shots in from a distance.

Climber: The climber consists of two Kraken x60s controlled by Talon FXs that is programmed to the manipulator controller. Our Climber is the WCP GreyT Telescoping Kit. 

The autonomous system uses a WPILib's odometry system as well as Path Planner to create an auto path that allows us to score multiple game pieces and taxi.

Copyright (c) 2024 Team 6925

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.