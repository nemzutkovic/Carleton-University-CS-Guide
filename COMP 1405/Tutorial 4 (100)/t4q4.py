output = {}
symbols = ["`", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", "{", "}", "[", "]", "|", '\', ":", ";", '"', "'", "<", ">", "?", "/", ".", ","']

def lettercount(string):
  for i in string:
    if i not in output and i not in symbols:
      output[i] = 1
    else:
      if i not in symbols:
        output[i] += 1
  return output

print(lettercount("Only counts the letters in a string."))