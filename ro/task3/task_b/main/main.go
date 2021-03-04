package main

import (
	"fmt"
	"sync"
	"time"
	"strconv"
)

func Barber(visitors chan int, done chan int) {
	//defer close(done)
	//defer close(visitors)
	fmt.Println("barber is sleeping")
	for {
		x := <-visitors
		//if !opened { , opened
		//	fmt.Println("All visitors are over")
		//	break
		//}
		//time.Sleep(time.Second)
		if strconv.Itoa(x) == "1" {
			fmt.Println("barber woke up")
		}
		fmt.Println("visitor " + strconv.Itoa(x) + " came, barber start work")
		time.Sleep(time.Second * 2)
		fmt.Println("barber finish work")
		done <- x
	}
}
func Visitor(visitors chan int, done chan int, wg *sync.WaitGroup, ind int) {
	//time.Sleep(time.Second)
	fmt.Println("visitor " + strconv.Itoa(ind) +" came and stood in line")
	visitors<-ind
	var i =<-done
	fmt.Println("visitor " + strconv.Itoa(i) + " is served and leaves the barber")
	defer wg.Done()
}

func main()  {
	var visitors = make(chan int)
	var done = make(chan int)
	var value = 3
	var wg sync.WaitGroup

	wg.Add(value)

	go Barber(visitors, done)

	for i := 1; i <= value ; i++ {
		go Visitor(visitors, done, &wg, i)
	}

	wg.Wait()
}