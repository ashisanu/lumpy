import lumpy


function program()
	contract 5
end


function contract(input:int)
	require
		input > 1
	end
	ensure
		input == 1
	end
	body
		print "Lol: " + string(input)
		input = 1
	end
end