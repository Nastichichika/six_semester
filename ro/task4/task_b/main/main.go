package main

import (
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"sync"
	"time"
)
var readWriteLock sync.RWMutex
func gardener(garden [][]int) {
	for {
		readWriteLock.RLock()
		for i := 0; i < 10; i++ {
			for j := 0; j < 10; j++ {
				if garden[i][j] == 0 {
					garden[i][j] = 1
				}
			}
		}
		readWriteLock.RUnlock()
		time.Sleep(2*time.Second)
	}
}
func nature(garden [][]int) {
	rand.Seed(time.Now().UTC().UnixNano())
	for {
		readWriteLock.Lock()
		count := rand.Intn(50)
		for i := 0; i < count; i++ {
			number_i := rand.Intn(10)
			number_j := rand.Intn(10)
			if garden[number_i][number_j] == 0 {
				garden[number_i][number_j] = 1
			} else {
					garden[number_i][number_j] = 0
			}
		}
		readWriteLock.Unlock()
		time.Sleep(1 * time.Second)
	}
}
func monitor1(garden [][]int) {
	file, err := os.Create("hello.txt")

	if err != nil{
		fmt.Println("Unable to create file:", err)
		os.Exit(1)
	}

	for {
		readWriteLock.RLock()
		for i := 0; i < 10; i++ {
			line := ""
			for j := 0; j < 10; j++ {
				line = line + strconv.Itoa(garden[i][j])
			}
			file.WriteString(line+"\n")
		}
		readWriteLock.RUnlock()
		file.WriteString("\n\n\n")
		time.Sleep(1 * time.Second)
	}
	defer file.Close()
}
func monitor2(garden [][]int) {
	for {
		readWriteLock.RLock()
		for i := 0; i < 10; i++ {
			for j := 0; j < 10; j++ {
				fmt.Print(garden[i][j], " ")
			}
			fmt.Println()
		}
		fmt.Println()
		readWriteLock.RUnlock()
		time.Sleep(2 * time.Second)
	}
}
func main() {
	var garden [][]int

	var wg sync.WaitGroup
	for i := 0; i < 10; i++ {
		var row []int
		for j := 0; j < 10; j++ {
			row = append(row, 0)
		}
		garden = append(garden, row)
	}
	wg.Add(4)
	go gardener(garden)
	go nature(garden)
	go monitor2(garden)
	go monitor1(garden)
	wg.Wait()
}
