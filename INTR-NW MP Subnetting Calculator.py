'''''''''''''''' INTR-NW MACHINE PROJECT: SUBNETTING CALCULATOR '''''''''

origNA=input("Network Address: ") 
origNA=origNA.split('.')
NAoct1=int(origNA[0])
NAoct2=int(origNA[1])
NAoct3=int(origNA[2])
NAoct4=int(origNA[3])

subMask=input("Subnet Mask(ex. 255.255.255.0): ")
''' if input is /24 format:
subMask=n.split('/')
subnet=int(subMask[1])
print(subnet)
'''
subMask=subMask.split('.')
Suboct1=int(subMask[0])
Suboct2=int(subMask[1])
Suboct3=int(subMask[2])
Suboct4=int(subMask[3])

###############################################
def binary2deci(binaryN):
    powerOf2=1
    deciConvert=0
    while binaryN > 0:
        binary = binaryN%10
        binaryN//=10
        deciConvert+=binary*powerOf2
        powerOf2*=2
    return(deciConvert)
# SAMPLE x=binary2deci(11100000)
# SAMPLE print(x)
###############################################
def deci2binary(n):
    b128= n//128
    n= n%128
    b64= n//64
    n= n%64
    b32= n//32
    n= n%32
    b16= n//16
    n= n%16
    b8= n//8
    n= n%8
    b4= n//4
    n= n%4
    b2= n//2
    n= n%2
    b1=n
    converted=str(b128)+str(b64)+str(b32)+str(b16)+str(b8)+str(b4)+str(b2)+str(b1)
    return(converted)
####################################################################################################
''' OPTIONAL PART '''
def binaryFormWithPeriod(f,n1,n2,n3,n4):  #writes in 11111111.10010100.00000000.00000000 way  
    boct1= f(n1)
    boct2= f(n2)
    boct3= f(n3)
    boct4= f(n4)
    print(boct1,boct2,boct3,boct4,sep='.')
binaryFormWithPeriod(deci2binary,NAoct1,NAoct2,NAoct3,NAoct4)
binaryFormWithPeriod(deci2binary,Suboct1,Suboct2,Suboct3,Suboct4)   
####################################################################################################
def binaryFormNoPeriod(f,n1,n2,n3,n4):  #writes in 11111111.10010100.00000000.00000000 way  
    boct1= f(n1)
    boct2= f(n2)
    boct3= f(n3)
    boct4= f(n4)
    return(boct1+boct2+boct3+boct4)

strbOrigNA=binaryFormNoPeriod(deci2binary,NAoct1,NAoct2,NAoct3,NAoct4)
print(strbOrigNA)
strbOrigSubMsk=binaryFormNoPeriod(deci2binary,Suboct1,Suboct2,Suboct3,Suboct4)
print(strbOrigSubMsk)
#####################################################################################################
def netmask(n): #counts how many 1's per octet
    rev=0
    while n > 0:
        rev= rev*10 + n%10
        n//=10
    netmask=0
    while rev > 0:
        digit= rev%10
        rev//=10
        if digit==1:
            netmask+=1
    return(netmask)
##################################################################

# Netwowrk Adress in binary (per octet)
bNAoct1=int(deci2binary(NAoct1)) # 192-->11000000
bNAoct2=int(deci2binary(NAoct2)) # 168-->10101000
bNAoct3=int(deci2binary(NAoct3)) # 1-->00000001
bNAoct4=int(deci2binary(NAoct4)) # 0-->00000000

# Subnet Mask in binary (per octet)
bSuboct1=int(deci2binary(Suboct1))
bSuboct2=int(deci2binary(Suboct2))
bSuboct3=int(deci2binary(Suboct3))
bSuboct4=int(deci2binary(Suboct4))
##########################################
''' Subnet Mask of orig NA '''
ntmsk1=netmask(bSuboct1)
ntmsk2=netmask(bSuboct2)
ntmsk3=netmask(bSuboct3)
ntmsk4=netmask(bSuboct4)
origSubMsk=ntmsk1 + ntmsk2 + ntmsk3 + ntmsk4
print('Subnet Mask: /',origSubMsk,sep='')
############################################
''' SUBNETTING TIME!!!!!!!!!!!!!! '''

nSubnet=int(input("Number of Subnets: "))
origSubnet=nSubnet
copySubnet=nSubnet
print("===============================================================================")
# this part still needs editing for subnets that are not a power of 2
nBits=0
while nSubnet > 1:
    nSubnet=nSubnet//2
    nBits+=1
print(nBits,'bits to borrow')
########################################

#adding the bits to borrow 
newSubMsk= origSubMsk + nBits
print('New Subnet Mask: /',newSubMsk,sep='')
hostBits=32-newSubMsk
nHost=1
while hostBits >0:
    nHost=nHost*2
    hostBits-=1
nHost=nHost-2
print("Hosts/Net:",nHost)# or for python u cn jst use 2**hostBits-2
print()

# bits to add
n=nBits
result=0
while n > 0:
    result=result*10+1
    n-=1
# print(result) # in 11111... (integer)form'''
#############################################################################
''''''''''''''''''''''''''''' NETWORK PORTION '''''''''''''''''''''''''''''''''''
bOrigNA=int(strbOrigNA)
bOrigSubMsk=int(strbOrigSubMsk)
revOrigNA=0
revOrigSubMsk=0
while bOrigNA > 0 and bOrigSubMsk>0:
    revOrigNA= revOrigNA*10 + bOrigNA%10
    bOrigNA= bOrigNA//10
    revOrigSubMsk=revOrigSubMsk*10 + bOrigSubMsk%10
    bOrigSubMsk//=10 
print("original binary NA in reverse:",revOrigNA) # for tracing purposes
print("original binary Subnet mask in reverse:",revOrigSubMsk) # for tracing purposes

''''''''''''''''''''''''''''''''' ANDing '''''''''''''''''''''''''''''
NAportion=0
while revOrigSubMsk>0:
    digiNA= revOrigNA % 10
    revOrigNA//=10
    digiSubMsk= revOrigSubMsk %10
    revOrigSubMsk//=10
    if digiNA==1 and digiSubMsk==1:
        digit=1
    else:
        digit=0
    NAportion=NAportion*10+digit
print("ANDed network address in binary:",NAportion)
strNAportion=str(NAportion) # to be added with each subnet and the host bits
print()
#############################################################################
''''''''''''''''''''''''''''' HOST PORTION '''''''''''''''''''''''''''''''''''
hostBits=32-newSubMsk
HostPortionBA=0
while hostBits>0:
    HostPortionBA=HostPortionBA*10+1
    hostBits-=1
strHostPortionBA=str(HostPortionBA)

hostBits=32-newSubMsk
strHostPortionNA=''
while hostBits>0:
    strHostPortionNA+="0"
    hostBits-=1
print()    
#############################################################################
''' the following functions will be used in the subnetting proper to
translate from binary to decimal NOT YET SURE IF USEFUL'''
def IPadd(f,n):
    rev=""
    j=1
    while j<=4:
        k=1
        while k<=8:
            rev+=str(n%10)
            n//=10
            k+=1
        j+=1
    # print(rev)for tracing purposes

    rev=int(rev)
    IPadd=""
    convert=0
    j=1
    while j<=4:
        octet=""
        k=1
        while k<=8:
            octet+=str(rev%10)
            rev//=10
            k+=1
        j+=1
        nOctet=int(octet)
        convert=f(nOctet)
        if j>4:
            IPadd+=str(convert)
        else:
            IPadd+=str(convert)+"."
    return(IPadd)

#############################################################################
''''''''''''''''''''''''''''' SUBNETTING PROPER '''''''''''''''''''''''''''''''''''
# this part still needs editing for subnets that are not a power of 2
borrowedBits=0
x=0
while x<origSubnet:
    print("SUBNET", x)
    ########################################################
    bBorrowedBits= int(deci2binary(borrowedBits))
    #print(bBorrowedBits) # for tracing purposes
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    strBorrowedBits=''
    j=nBits
    while j>0:
        strBorrowedBits+=str(bBorrowedBits%10)
        j-=1
        bBorrowedBits//=10
        #print(strBorrowedBits)
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''   
    revstrBorrowedBits=''
    strBorrowedBits=int(strBorrowedBits)
    j=nBits
    while j>0:
        revstrBorrowedBits+=str(strBorrowedBits%10)
        j-=1
        strBorrowedBits//=10
    #print(revstrBorrowedBits)
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    borrowedBits+=1
    x+=1
    #######################################################
    strNA=strNAportion + revstrBorrowedBits + strHostPortionNA
    bNA= int(strNA)
    strBA=strNAportion + revstrBorrowedBits + strHostPortionBA
    bBA=int(strBA)
    bFA=bNA+1
    bLA=bBA-1
    NA=IPadd(binary2deci,bNA)
    BA=IPadd(binary2deci,bBA)
    FA=IPadd(binary2deci,bFA)
    LA=IPadd(binary2deci,bLA)
    print("Network Address:\t", NA,'/',str(newSubMsk),sep="")
    print("Broadcast Address:\t",BA)
    print("First Usable Address:\t",FA)
    print("Last Usable Address:\t",LA)
    print()

''''''' BONUS FEATURE: IP Addressing Module '''''''
y=0
while y< copySubnet:
    nHostSubnet=int(input("Number of Host in Subnet "+str(y)+": "))
    y+=1


