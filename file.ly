import lumpy


function program()
	var arr = new Array<int>
	loop var i = 0 to 20
		print "set: " + string(i)
		arr[i] = i*2
	end
	
	print arr.length
	
	loop var i in arr
		print "Posi: " + string(i)
	end
end