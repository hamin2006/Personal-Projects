import pygame,time,random,sys
pygame.init()
displayWidth=800
displayHeight=600
gameDisplay = pygame.display.set_mode((displayWidth,displayHeight))
clock= pygame.time.Clock()
pygame.display.set_caption('Agility Racer') 
my_car=pygame.image.load('car.png') #our car
picture = pygame.transform.scale(my_car, (200,200)) #scaling car
road1=pygame.image.load('road.png')
road=pygame.transform.scale(road1,(800,600)) #main game road background
menu_bg=pygame.image.load('background.jpg') #menu background
white=(255,255,255) #colours
black=(0,0,0)
red=(255,0,0)
bright_red=(254,84,66)
blue=(0,0,255)
bright_blue=(0,96,255)
green=(0,255,0)
bright_green=(150,255,0)
pause=False #pause menu
def car(width,height): #car blit to screen
    gameDisplay.blit(picture,(width,height))
def background(): #road blit to screen
    gameDisplay.blit(road,(0,0))
    
def text_objects(text, font): #text setup
    textSurface = font.render(text, True, red)
    return textSurface,textSurface.get_rect()
def message_display(text): #text setup and blit to screen
    largeText = pygame.font.Font('freesansbold.ttf',110)
    TextSurf, TextRect = text_objects(text, largeText)
    TextRect.center = ((displayWidth/2),(displayHeight/2))
    gameDisplay.blit(TextSurf, TextRect)
    pygame.display.update()
    time.sleep(2)
    game_play()
def crashed():  #crashed text
    message_display('You Crashed')
def enemyUse(enemyX,enemyY,enemy): #enemy vehicles setup and blit to screen
    if enemy==0:
        enemy_ca=pygame.image.load('Enemy.png')
        enemy_car=pygame.transform.scale(enemy_ca, (111,204))
    gameDisplay.blit(enemy_car,(enemyX,enemyY))
def scoreboard(car_passed,score): #scoreboard font and location setup and blit tp screen
    font=pygame.font.SysFont(None,25)
    text=font.render('Passed: '+str(car_passed),True,black)
    score=font.render('Score: '+str(score),True,blue)
    gameDisplay.blit(text,(0,50))
    gameDisplay.blit(score,(0,30))
def button(message,x,y,width,height,colour,hcolour,action=None): #button and mouse setup 
    mouse=pygame.mouse.get_pos()
    click=pygame.mouse.get_pressed()
    if x+width>mouse[0]>x and y+height>mouse[1]>y:
        pygame.draw.rect(gameDisplay,hcolour,(x,y,width,height))
        if click[0]==1 and action!=None: #six button types
            if action=="play":
                game_play()
            elif action=="quit":
                pygame.quit()
                quit()
                sys.exit()
            elif action=="introduction":
                introduction()
            elif action=="menu":
                introLoop()
            elif action=="pause":
                Pause()
            elif action=="unpause":
                Unpause()
    else:
        pygame.draw.rect(gameDisplay,colour,(x,y,width,height)) 
    smallText = pygame.font.Font('freesansbold.ttf',20)
    TextSurf, TextRect = text_objects(message,smallText) #text in buttons
    TextRect.center = ((x+(width/2)),(y+(height/2)))
    gameDisplay.blit(TextSurf, TextRect)
def introLoop(): #main pre-game menu loop
    global event 
    intro=True
    while intro:
        for event in pygame.event.get():
            if event.type==pygame.QUIT:
                pygame.quit()
                quit()
                sys.exit()
        gameDisplay.blit(menu_bg,(0,0))
        largeText = pygame.font.Font('freesansbold.ttf',115)
        TextSurf, TextRect = text_objects("Agility Racer!", largeText)
        TextRect.center=(400,100)
        gameDisplay.blit(TextSurf, TextRect)
        button("START",150,520,100,50,green,bright_green,"play") #main menu buttons
        button("QUIT",550,520,100,50,bright_red,red,"quit")
        button("INSTRUCTIONS",300,520,200,50,blue,bright_blue,"introduction")
        pygame.display.update()
        clock.tick(60) #refresh rate (adjustable if monitor screen doesn't support frame rate)
def introduction(): #Instructions page
    global event
    introduction=True 
    while introduction:
        for event in pygame.event.get():
            if event.type==pygame.QUIT:
                pygame.quit()
                quit()
                sys.exit()
        gameDisplay.blit(menu_bg,(0,0))
        smallText=pygame.font.Font('freesansbold.ttf',20)
        largeText=pygame.font.Font('freesansbold.ttf',80)
        TextSurf, TextRect = text_objects("Instructions", largeText)
        TextRect.center=(400,100)
        gameDisplay.blit(TextSurf, TextRect)
        sTextSurf, sTextRect=text_objects("Press pause to pause and use the left and right arrow keys to switch lanes ", smallText)
        sTextRect.center=(400,200)
        gameDisplay.blit(sTextSurf, sTextRect)
        aTextSurf, aTextRect=text_objects("For every 10 cars you pass,the speed at which you travel will increase", smallText)
        aTextRect.center=(400,250)
        gameDisplay.blit(aTextSurf, aTextRect)
        bTextSurf, bTextRect=text_objects("Keep your distance from surrounding drivers and let's see who can get the", smallText)
        bTextRect.center=(400,300)
        gameDisplay.blit(bTextSurf, bTextRect)
        cTextSurf, cTextRect=text_objects("Highest Score! ", smallText)
        cTextRect.center=(400,350)
        gameDisplay.blit(cTextSurf, cTextRect)
        dTextSurf, dTextRect=text_objects("Good luck drivers, and may the most skilled driver win", smallText)
        dTextRect.center=(400,400)
        gameDisplay.blit(dTextSurf, dTextRect)
        button("BACK", 600, 450, 100, 50, blue, bright_blue,"menu")
        pygame.display.update()
        clock.tick(60)#refresh rate (adjustable if monitor screen doesn't support frame rate)
def Pause():#in game pause menu 
    global pause
    pause=True
    while pause:
        for event in pygame.event.get():
            if event.type==pygame.QUIT:
                pygame.quit()
                quit()
                sys.exit()
        gameDisplay.blit(menu_bg,(0,0)) #blit pause menu background
        largeText=pygame.font.Font('freesansbold.ttf',120)
        TextSurf, TextRect = text_objects("Paused", largeText) #pause menu text
        TextRect.center = ((displayWidth/2),(displayHeight/2))
        gameDisplay.blit(TextSurf,TextRect)
        button("CONTINUE",150,450,150,50,green,bright_green, "unpause") #pause men buttons
        button("RESTART",350,450,150,50,blue,bright_blue, "play") 
        button("MENU",550,450,200,50,bright_red,red, "menu")
        pygame.display.update()
        clock.tick(60)#refresh rate (adjustable if monitor screen doesn't support frame rate)
def Unpause(): #unpause button/continue button
    global pause
    pause=False
def game_play(): #main game loop
    global event,pause
    crash_contact=False #constant values
    enemy_speed=7
    enemy=0
    enemyX=random.randrange(150,(displayWidth-200))
    enemyY=-750
    enemyWidth=83
    enemyHeight=153
    width=displayWidth*0.375
    height=displayHeight*0.65
    x_change=0
    car_passed=0
    score=0
    level=0
    while not crash_contact: 
        for event in pygame.event.get():
            if event.type==pygame.QUIT: #quit functions
                pygame.quit()
                quit()
        if event.type==pygame.KEYDOWN:
            if event.key==pygame.K_LEFT: #key bindings
                x_change=-20
            if event.key==pygame.K_RIGHT:
                x_change=20
        if event.type==pygame.KEYUP:
            if event.key==pygame.K_LEFT or event.key==pygame.K_RIGHT:
                x_change=0
        width+=x_change #car x axis change
        background() #blit
        enemyY-=enemy_speed/4 #enemy moving in/ y axis change
        enemyUse(enemyX,enemyY,enemy)#enemy function
        enemyY+=enemy_speed
        car(width, height)#car blit (constantly changing)
        scoreboard(car_passed,score) #scoreboard on top left hand screen
        if width>570 or width<25: #road restrictions
            message_display('Out Of Bounds') 
        if width>displayWidth-225 or width<25:
            crashed()
        if enemyY>displayHeight: #scoreboard counting
            enemyY=0-enemyHeight
            enemyX=random.randrange(150,(displayWidth-170))
            enemy=0
            car_passed=car_passed+1
            score=car_passed*5
            if int(car_passed)%10==0 or level==0:
                level=level+1
                enemy_speed+=4
                largeText = pygame.font.Font('freesansbold.ttf',110)
                TextSurf, TextRect = text_objects("Level "+str(level), largeText)
                TextRect.center = ((displayWidth/2),(displayHeight/2))
                gameDisplay.blit(TextSurf, TextRect)
                pygame.display.update()
                time.sleep(2)
        if height<enemyY+enemyHeight: #crash restrictions
            if width>enemyX and width<enemyX+enemyWidth or width+100>enemyX and width+100<enemyX+enemyWidth:
                crashed()
        button("PAUSE",650,0,150,50,bright_blue,bright_green,"pause") #pause button on top right hand side 
        pygame.display.update()
        clock.tick(100)#refresh rate (adjustable if monitor screen doesn't support frame rate)
introLoop() #start menu
game_play() #main game play
pygame.quit() #quit functions(which can also be accessed through main menu)
quit()