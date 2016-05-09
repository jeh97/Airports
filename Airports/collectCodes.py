x = raw_input("Stuff: ")
count = 0
print "\t\t\t\t",
for i in range(0,len(x),3):
    print "<Dest>"+x[i:i+3]+"</Dest>",
    count+=1
    if(count >= 5):
        print
        print "\t\t\t\t",
        count = 0
    
    
