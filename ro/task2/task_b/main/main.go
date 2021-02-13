package main

import (
	"fmt"
	"time"
)


func Ivanov(value int, input chan int) {
	chanel1 := make(chan int)
	go Petrov(chanel1, input)

	for i := 0; i < value ; i++ {
		fmt.Println("Ivanov: START")
		time.Sleep(time.Second)
		fmt.Println("Ivanov: END")
		chanel1 <- 1
	}
	defer close(chanel1)

}

func Petrov(chanel1 chan int, input chan int) {
	chanel2 := make(chan int)
	fmt.Println("Petrov: WAITING")
	go Necheporchuk(chanel2, input)

	for {
		x, opened := <-chanel1
		if !opened {
			break
		}
		fmt.Println("Petrov: START")
		time.Sleep(time.Second)
		fmt.Println("Petrov: END")
		chanel2 <- x
	}
	defer close(chanel2)

}

func Necheporchuk(chanel2 chan int, input chan int) {
	fmt.Println("Necheporchuk: WAITING")
	sum:= 0
	for {
		x, opened := <-chanel2
		if !opened {
			break
		}
		fmt.Println("Necheporchuk: START")
		time.Sleep(time.Second * 3)
		sum = sum + x
		fmt.Println("Necheporchuk: END")
	}
	input <-sum
}

func main() {
	var input = make(chan int)
	go Ivanov(10, input)
	fmt.Println(<-input)
}
