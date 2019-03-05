subtotal = float(input("Subtotal="))
tippercentage = int(input("Tip="))
totalbill = float(subtotal * (tippercentage/100) + subtotal)
print("The total bill is $"+ str(totalbill))	