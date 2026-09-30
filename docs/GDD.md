# Coffee Shop Tycoon — Game Design Document

Version 1.0 · Android · Kotlin / Jetpack Compose · Min SDK 26

---

## 1. Story

You inherit a run-down coffee stall in a strip mall from your aunt, who left one handwritten note: *"Coffee is easy. People are hard. Figure out both."* You start with $500, one espresso machine held together with tape, one part-time cashier, and a single item on the menu.

The game has no combat and no villain — the antagonist is entropy: tired equipment, grumpy customers, thin margins, and your own impatience. Across the run you expand the stall into a café, then a multi-location chain, then a franchise empire. Every prestige ("Sell the Company") is narratively framed as cashing out and opening a *new, bigger* flagship location with the experience (not the cash) carried forward — the reason permanent bonuses survive but raw currency doesn't.

Flavor text (menu unlock blurbs, employee bios, achievement titles, random event pop-ups) carries the tone: warm, a little dry, never edgy. This is the entire "story" — there are no cutscenes or dialogue trees. It exists to make numbers feel like a place.

## 2. Game Loop

**Core loop (seconds):** Customer arrives → orders from available menu → order is queued to an available employee/machine → item is brewed/prepared over a duration modified by machine tier + employee skill → order is served → customer pays (tip modified by wait time, mood, and match to favorite drink) → review score is recorded → cash is added.

**Session loop (minutes):** Earn cash from the core loop → spend cash on menu unlocks, machine upgrades, hiring/training staff, and store decor → each purchase raises throughput, ticket size, or customer satisfaction → satisfaction/reputation raises customer arrival rate and VIP/influencer chance → repeat with a higher income ceiling.

**Meta loop (hours/days):** Accumulate enough lifetime revenue to make prestiging profitable → prestige to convert progress into permanent multipliers (Roasted Bean prestige currency) → restart the cash/store loop faster and reach further than the previous run → unlock research, achievements, and events along the way that are either permanent or account-wide.

**Idle loop (offline):** While the app is closed, a background production estimate keeps accruing (employees keep serving at a reduced idle rate) up to a 24-hour cap, delivered as a "Welcome back" summary + claimable lump sum on next launch, plus an optional WorkManager notification.

## 3. Core Gameplay

- **Tap-to-assist:** Tapping an active order slot gives a small manual speed boost (bridges early game before employee automation is fast) and produces a coin/steam particle burst.
- **Auto-serve:** Employees assigned to a station automatically pull queued orders and serve them; the player's job shifts from tapping to *allocating* — which stations to staff, which machines to upgrade, which menu items to unlock next.
- **Queue & patience:** Each customer has a patience timer; if it runs out before being served they leave (lost revenue + small reputation hit). Queue length is capped by store capacity (raised via seating upgrades).
- **Matching:** Serving a customer's favorite drink grants a tip bonus and higher review score; serving anything else still succeeds but with a smaller tip. Running out of an unlocked item's ingredients temporarily disables it until restocked (inventory system).
- **Reputation:** A slow-moving score (0–5 stars, shown as a rolling average of the last 200 reviews) that gates arrival rate of higher-value customer types (VIPs, influencers) and is the primary "quality" axis separate from raw throughput.

## 4. Progression

| Stage | Approx. lifetime revenue | Unlocks |
|---|---|---|
| 1. Stall | $0 | Espresso, 1 cashier, 1 machine |
| 2. Corner Café | $5K | Latte, Mocha, second employee slot, Milk Frother |
| 3. Neighborhood Shop | $50K | Americano, Cold Brew, tables, Grinder, first Research nodes |
| 4. Popular Café | $250K | Tea, Smoothies, Cake, Manager role, Industrial Machine |
| 5. Local Chain (2nd store) | $1M | Cookies, Donuts, outdoor seating, Marketing employee |
| 6. City Favorite | $5M | Sandwiches, Premium Coffee, drive-through, Trainer role |
| 7. Regional Brand | $25M | Iced Latte, Seasonal Drinks unlocked permanently, Robot Barista, Security |
| 8. First Prestige available | $100M | "Sell the Company" unlocked |
| 9+. Franchise Empire | scales per prestige | Second floor, AI Ordering research, Delivery research, deeper prestige upgrade tree |

Progression is gated by **soft cost curves** (menu items, upgrades) and **hard milestones** (revenue thresholds unlock categories like tables, research tier, second store), so the player always has both a "buy the next thing" action and a "reach for the next tier" goal.

## 5. Economy

### 5.1 Currencies

- **Cash ($)** — primary currency. Earned from sales, spent on everything operational (menu unlocks, machines, staff, decor, inventory restock).
- **Roasted Beans (◈)** — prestige currency. Earned only on prestige, based on lifetime cash earned this run. Spent in the permanent Prestige Upgrade tree (never resets).
- **Loyalty Points (♥)** — soft "reputation currency" earned passively from high review scores and events; spent on store-decor and research discounts. Does **not** reset on prestige (represents brand goodwill) but earn rate resets to a low baseline until reputation is rebuilt, so it doesn't trivialize the next run.
- **Event Tokens** — time-limited currency earned only during a live seasonal event, spent in that event's shop, expire (convert to a small cash bonus) when the event ends.

### 5.2 Costs & Scaling

- Menu items: fixed one-time unlock cost, scaled roughly ×3–5 per stage tier.
- Machines/upgrades: `cost(level) = baseCost * growth^level` with `growth` in the 1.12–1.18 range depending on category (kept low relative to typical idle games specifically to avoid runaway inflation — see §14).
- Employees: hiring cost + linear salary that scales with level; salary is paid automatically from revenue each in-game "day" tick, creating a soft cap that rewards efficiency over blind stacking.
- Research: cash + (later tiers) Loyalty Points, one-time, tree-gated by prerequisites.

### 5.3 Income Sources

Order revenue (price − ingredient cost + tip), passive Loyalty trickle, offline earnings, daily login bonuses, achievement one-off rewards, event rewards.

### 5.4 Sinks

Menu unlocks, machine upgrades, staff hiring/salaries/training, store decor, inventory restocking, research, event shop.

## 6. Unlockables

**Menu (14 items):** Espresso → Latte → Mocha → Americano → Cold Brew → Iced Latte → Tea → Smoothies → Cake → Cookies → Donuts → Sandwiches → Premium Coffee → Seasonal Drinks (rotating, event-gated).

**Machines (10):** Coffee Machine, Premium Machine, Industrial Machine, Milk Frother, Grinder, Oven, Blender, Ice Machine, Self Checkout, Robot Barista — each independently upgradeable (levels affect speed/quality/capacity).

**Store upgrades (10):** Tables, Decoration, Music, Lighting, Walls, Plants, Floor, Outdoor Seating, Drive-Through, Second Floor.

**Staff roles (8):** Cashier, Barista, Cleaner, Manager, Marketing, HR, Trainer, Security.

## 7. Research

Tree with 8 branches (Automation, Marketing, Recipes, Efficiency, Customer Happiness, Furniture, AI Ordering, Delivery), each 4–6 nodes deep, prerequisite-gated (must own parent node + revenue/reputation threshold). Effects are permanent for the *current prestige run* (reset on prestige, unless re-purchased with a discount from Loyalty Points carried over).

## 8. Prestige System

"Sell the Company": available once lifetime cash-this-run ≥ $100M (first threshold; later thresholds scale). Prestiging:

- Resets: Cash, menu unlocks, machines, staff, store upgrades, research, current-run stats.
- Keeps: Roasted Beans (gained from this run, formula in §14), Prestige Upgrade tree purchases, Achievements, Loyalty Points (at reduced ongoing rate), Statistics (lifetime), Settings.
- Roasted Bean prestige upgrades grant permanent multipliers: global income, starting cash on new run, reduced menu-unlock costs, faster customer patience decay resistance, higher offline cap (up to 24h max), auto-unlock of early menu items, etc.

## 9. Achievements

100+ achievements across categories: Revenue milestones, Customers served, Drinks sold (per type + total), Employee levels/roster size, Machine upgrades, Research completed, Prestige count, Store upgrades owned, Perfect-review streaks, Event participation, Playtime, "Secret" achievements (e.g., serve 10 VIPs in a row without a miss). Each grants a small one-off cash/Loyalty reward; some grant cosmetic-only badges shown on the Statistics screen.

## 10. Daily Rewards

7-day cycle of escalating cash/Loyalty/Event Token rewards; a separate 30-day cumulative calendar with a milestone bonus (cosmetic decor item or a Roasted Bean chunk) at day 30. Missing a day doesn't reset the 30-day calendar (it's cumulative, not a strict streak), but does reset the 7-day streak bonus multiplier — a deliberate leniency so returning players aren't punished harder than they already are by lost offline time.

## 11. Offline Earnings

Idle income accrues at a reduced rate (employees keep working, but slower and without manual tap-assist or fresh customer arrivals) capped at **24 hours**. On return, a summary dialog shows elapsed time, estimated customers served, and cash earned, with a single "Collect" action. A WorkManager periodic worker can post a one-time local notification once the offline estimate crosses a meaningful threshold, if enabled in Settings.

## 12. Events

**Seasonal (calendar-scheduled, ~1–2 weeks each):** Christmas, Halloween, Summer, Black Friday, Coffee Festival — each adds a themed Seasonal Drink, a themed store-decor set, and an Event Token shop.

**Random (in-run, low-probability per tick, single-serving):** Food Inspector (pass/fail mini-check affecting reputation), Celebrity Visit (huge one-off tip + reputation if served well), Machine Breakdown (a machine goes offline until repaired/paid), Power Outage (temporary throughput penalty), Rain (arrival rate boost — people want coffee), Influencer Review (reputation swing up or down based on recent service quality), Coffee Bean Shortage (ingredient cost spike / temporary menu item disable).

## 13. Shop

Real-money-optional **Store** tab: cosmetic decor bundles, a time-skip consumable (simulate N hours of offline earnings instantly), a "Roasted Bean pack" (optional IAP, not required for progression — see monetization note below), and a single non-intrusive rewarded-ad slot ("Watch an ad to double this offline summary"). No pay-to-win pricing on core progression; IAP is convenience/cosmetic only, consistent with the idle-tycoon genre norm and Play Store policy expectations.

## 14. Difficulty Curve & Balancing

- Cost growth rates are deliberately shallow (1.12–1.18×/level for machines, ~3–5× per tier for menu items) rather than the steep 1.3–1.5× common in aggressive idle games — the goal is that **every purchase remains individually meaningful** rather than being immediately trivialized by the next.
- Revenue growth is bounded by three multiplicative factors only: machine tier, employee level/count, and reputation-gated customer mix — no unbounded "prestige layer stacking on prestige layer" (i.e., only one prestige currency feeds multipliers, not a chain of three or four), which keeps late-game numbers from becoming unreadable scientific notation.
- Roasted Bean reward formula: `beans = floor(sqrt(lifetimeCashThisRun / 1_000_000))`, giving diminishing but always-positive returns and making each prestige decision a clear, calculable trade-off.
- Target session shape: meaningful decision every 30–90 seconds early game, every few minutes mid-game, "check in and reallocate" by late game — idle-friendly without feeling empty.

## 15. Save System

Local-first via Room (all game state: currencies, unlocks, employees, machines, research, achievements, statistics, event state) + DataStore (settings/preferences). Autosave on every significant state mutation (debounced) and on app background/stop via `ProcessLifecycleOwner`. A single save slot (per Play Games account if signed in, otherwise local-only) — no manual save/load UI, consistent with the idle-tycoon genre; a "Statistics" screen exposes lifetime totals as the closest thing to a save-file inspector. Migrations are additive-only Room `Migration` objects — never destructive — so a schema change never silently wipes a player's progress.

## 16. Expansion Ideas (post-launch)

- Second and third physical store locations as their own idle "sub-loops" feeding one shared economy.
- Franchise mode: license the brand to NPC operators for a trickle income stream.
- Leaderboards (Play Games Services) for lifetime revenue and fastest-to-first-prestige.
- Cloud save sync.
- Recipe customization (adjust a drink's price/quality/cost trade-off directly).
- More event types and a rotating "featured research" node.
