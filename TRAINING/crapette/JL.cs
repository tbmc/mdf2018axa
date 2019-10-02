using System;
using System.Collections.Generic;
using System.Linq;

namespace BattleDev
{
    public class Program
    {
        public static void Main()
        {
            var stackCount = int.Parse(Console.ReadLine()) + 1;
            Stack<int>[] stacks = new Stack<int>[stackCount];
            for (int i = 0; i < stackCount; i++)
            {
                stacks[i] = new Stack<int>();
            }
            int nbCards = int.Parse(Console.ReadLine());
            string line = Console.ReadLine();
            foreach (var card in line.Split(' '))
            {
                stacks[0].Push(int.Parse(card));
            }

            List<string> moves = new List<string>();

            while (!stacks.Skip(1).Any(s => s.Count == nbCards))
            {
                bool moved = false;

                //for (int i = 0; i < stackCount - 1; i++)
                //{
                moved = false;
                //Pile courante vide, on passe a la suivante
                //if (stacks[i].Count == 0)
                //{
                //    continue;
                //}
                var cards = stacks
                    .Select((s, idx) => (Item1: s, idx: idx))
                    .Where(s => s.Item1.Count > 0)
                    .Select(s => (Item1: s.Item1.Peek(), idx: s.idx))
                    .OrderByDescending(s => s.Item1)
                    .ToArray();

                var (card, i) = (0, 0);
                if (cards[0].idx == 0)
                {
                    if (cards.Length != stackCount)
                    {
                        (card, i) = cards[0];

                    }
                    else
                    {
                        (card, i) = cards.Last();
                    }
                }
                else
                {
                    (card, i) = cards[1];
                }

                //On essaie de placer sur la carte N+1
                for (int j = 0; j < stackCount; j++)
                {
                    //Si pile courante ou pile vide, on sort
                    if (j == i || stacks[j].Count == 0)
                        continue;
                    if (stacks[j].Peek() == card + 1)
                    {
                        stacks[j].Push(stacks[i].Pop());
                        moves.Add($"{card} {j}");

                        moved = true;
                        break;
                    }
                }

                if (!moved)
                {
                    //On essaie de placer sur une pile vide
                    for (int j = 0; j < stackCount; j++)
                    {
                        if (j == i)
                            continue;
                        if (stacks[j].Count == 0)
                        {
                            stacks[j].Push(stacks[i].Pop());
                            moves.Add($"{card} {j}");

                            moved = true;
                            break;
                        }
                    }
                }

                //    if (moved)
                //        break;
                //}
                if (!moved || moves.Count > 2 && moves.Last() == moves[moves.Count - 3])
                {
                    Console.WriteLine("fail");
                    return;
                }
            }

            Console.WriteLine(string.Join(";", moves));
        }
    }
}
