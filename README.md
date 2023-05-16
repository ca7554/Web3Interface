# Web3Interface

# Table of Contents
1. [Intro](#intro)
2. [Description](#description)

## Intro
This repository is a personal project, created for fun and to simplify access to Web3 services. It also serves as a platform to practice three key software design principles: object-oriented design, DRY (Don't Repeat Yourself), and the Open-Closed Principle.

## Description
The aim of this project was to design a library that could interact with multiple blockchains, such as Polygon and Binance, and access their respective services like QuickSwap and PancakeSwap. Given that many of these blockchains are forks of one another, they often share similar functionality and even identical code. This similarity can be leveraged through object-oriented design to avoid code repetition. By employing interfaces and abstract classes, this library can be extended to accommodate blockchains that operate differently, such as Cardano, among others. This flexibility allows for the expansion of the library without needing to modify existing, functional code within it. Moving forward, I plan to continue expanding this library's capabilities, with the goal of supporting even more diverse blockchain technologies.
