read x
read y
if [ "$x" -eq "$y" ]; then
    echo "$x is equal"
fi
if [ "$x" -lt "$y" ]; then
    echo "$x is lesser than $y"
fi
if [ "$x" -eq "$y" ]; then
    echo "$x is greater than $y"
fi
