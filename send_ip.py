# ********************************************************
# some important libraries needed
# based on https://elinux.org/RPi_Email_IP_On_Boot_Debian
# ********************************************************
import subprocess
import smtplib
import socket
from email.mime.text import MIMEText
import datetime
import time
import platform # to get host name

# ********************************************
# Email Account Information
# Change to your own account information
# ********************************************
Recipient = '????@gmail.com'
Sender    = '????@gmail.com'
Password = '????'


# ********************************************
# Email Server Setup
# DO NOT CHANGE
# ********************************************
#smtpserver = smtplib.SMTP('smtp.gmail.com', 587)
tries = 0
while True:
    if (tries > 60):
        exit()
    try:
        smtpserver = smtplib.SMTP('smtp.gmail.com', 587, timeout=30) # Server to use.
        break
    except Exception as e:
        tries = tries + 1
        time.sleep(1)
smtpserver.ehlo()
smtpserver.starttls()
smtpserver.ehlo
smtpserver.login(Sender, Password)

# ********************************************
# Linux-Specific
# DO NOT CHANGE
# ********************************************
today = datetime.date.today()
arg= 'ip route list'
p  = subprocess.Popen(arg,shell=True,stdout=subprocess.PIPE)
data = p.communicate()
split_data = data[0].split()
hostName = platform.node()
ipaddr = split_data[split_data.index('src')+1]
routerAddress = split_data[split_data.index('via')+1]
timeStr = time.strftime('%b %d %Y, %H:%M:%S')

# ********************************************
# Construct Email Message
# DO NOT CHANGE
# ********************************************
msg_body =  '*************************************************************\n'
msg_body += 'Pi Hostname\t\t' + hostName + '\n'
msg_body += 'IP Address\t\t' + ipaddr + '\n'
#msg_body += 'Router\' IP\t\t' + routerAddress + '\n'
msg_body += '*************************************************************\n'
msg_body += 'TCSS573 @ UW Tacoma.\n\nGenerated at: ' + timeStr + '\n'


Message = MIMEText(msg_body)
Message['Subject'] = 'TCSS573: Your RPi IP for %s' % today.strftime('%b %d %Y')
Message['From'] = Sender
Message['To'] = Recipient

smtpserver.sendmail(Sender, [Recipient], Message.as_string())
smtpserver.quit()
